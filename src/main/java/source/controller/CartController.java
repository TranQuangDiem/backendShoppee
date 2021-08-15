package source.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import source.entity.CartItem;
import source.entity.Product;
import source.jwt.JwtTokenProvider;
import source.payload.CartItems;
import source.payload.CartRequest;
import source.payload.Message;
import source.payload.Order;
import source.service.CartItemService;
import source.service.CartService;
import source.service.UserService;

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
    @Autowired
    CartService cartService;
    @PostMapping("/cart")
    public ResponseEntity saveCart(@RequestBody CartRequest cart, @RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            for (CartItems cartRequest : cart.getCartItems()) {
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
            List<CartItems> cartItems = cartItemService.findByUserId(userId);
            CartRequest cartRequest = new CartRequest();
            cartRequest.setCartItems(cartItems);
            return ResponseEntity.ok().body(cartRequest);
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("token không hợp lệ"));
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity order(@RequestBody Order order , @RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            cartService.save(order,userId);
            return ResponseEntity.ok().body(new Message("lưu thành công"));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("token không hợp lệ"));
        }
    }
}
