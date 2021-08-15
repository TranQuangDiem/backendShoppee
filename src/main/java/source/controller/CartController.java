package source.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import source.entity.CartItem;
import source.entity.Product;
import source.jwt.JwtTokenProvider;
import source.payload.CartRequest;
import source.payload.Message;
import source.service.CartItemService;
import source.service.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    ModelMapper mapper;
    @Autowired
    CartItemService cartItemService;
    @PostMapping("/cart")
    public ResponseEntity saveCart(@RequestBody List<CartRequest> cartRequestList,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            for (CartRequest cartRequest : cartRequestList) {
                CartItem cartItem = new CartItem();
                cartItem.setIdc(cartRequest.getIdc());
                Product product = mapper.map(cartRequest.getNewProduct(), Product.class);
                cartItem.setProduct(product);
                cartItem.setUser(userId);
                cartItem.setActive(1);
                cartItem.setQuantity(cartRequest.getQuantity());
                cartItemService.save(cartItem);
            }
            return ResponseEntity.ok().body(new Message("lưu thành công"));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("token không hợp lệ"));
        }
    }
    @GetMapping("/cart")
    public ResponseEntity getCart(@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            List<CartRequest> cartRequests = cartItemService.findByUserId(userId);
            return ResponseEntity.ok().body(cartRequests);
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("token không hợp lệ"));
        }
    }
}
