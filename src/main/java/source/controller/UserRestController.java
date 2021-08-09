package source.controller;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import source.entity.Role;
import source.entity.User;
import source.jwt.JwtAuthenticationFilter;
import source.jwt.JwtTokenProvider;
import source.payload.*;
import source.model.CustomUserDetails;
import source.service.ForgotPasswordService;
import source.service.RoleService;
import source.service.UserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực thông tin người dùng Request lên
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        User user = userService.findByEmail(loginRequest.getEmail());

        // Map thành object đích
        UserReponse userDto = mapper.map(user, UserReponse.class);
        return ResponseEntity.ok().body(new LoginResponse(jwt,userDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Message("Email hoặc password không hợp lệ"));
        }
    }
    // api/register api đăng ký
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
        if(user.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"))
            return ResponseEntity.badRequest().body(new Message("Email không hợp lệ"));
        if (userService.findByEmail(user.getEmail())!=null)
            return ResponseEntity.badRequest().body(new Message("Email đã tồn tại trong hệ thống"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findById(1));
        System.out.println(user);
        userService.save(user);
        UserReponse userDto = mapper.map(user, UserReponse.class);
        String jwt = tokenProvider.generateToken(userService.findByEmail(user.getEmail()));
        return ResponseEntity.ok().body(new LoginResponse(jwt,userDto));
    }
    @PutMapping("user/edit")
    public ResponseEntity editUser(@RequestBody UserEditDTO userEditDTO, @RequestHeader("Authorization") String jwt){
        String [] a = jwt.split(" ");
        if (a.length!=2)
            return ResponseEntity.status(401).body("Authenticaton");
        long id = tokenProvider.getUserIdFromJWT(a[1]);
        User user = userService.findById(id);
        if (user!=null) {
//            userService.edit(userEditDTO, user);
            return ResponseEntity.ok().body("cập nhật thông tin user thành công");
        }else {
            return ResponseEntity.status(400).body(new Message("Tài khoản không tồn tại trong hệ thống"));
        }
    }
    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public Message randomStuff(@RequestHeader("Authorization") String jwt){
        String [] a = jwt.split(" ");
        System.out.println( tokenProvider.getUserIdFromJWT(a[1]));
        return new Message("JWT Hợp lệ mới có thể thấy được message này");
    }
    @PostMapping("/user/forgotPassword")
    public ResponseEntity forgotPass(@RequestBody String email){
        if (email.trim()==null)return ResponseEntity.badRequest().body(new Message("vui lòng nhập email"));
        User user = userService.findByEmail(email);
        if (user!=null){
                try {
                    forgotPasswordService.save(email);
                    return ResponseEntity.ok().body(new Message("Vui lòng kiểm tra email để lấy OPT"));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().body(new Message("Hệ thống gặp sự cố ! vui lòng thử lại sau"));
                }
        }
        return ResponseEntity.badRequest().body(new Message("email không tồn tại trong hệ thống"));
    }

}
