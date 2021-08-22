package source.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import source.entity.Cart;
import source.entity.CartItem;
import source.entity.Product;
import source.jwt.JwtTokenProvider;
import source.payload.*;
import source.service.CartItemService;
import source.service.CartService;
import source.service.StatusService;
import source.service.UserService;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    StatusService statusService;
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
    @GetMapping("/orderManager")
    public ResponseEntity orderManager(@RequestHeader("Authorization") String jwt, @RequestParam("status")Optional<Long> status,@RequestParam(required = false, value = "_page") Integer page,
                                       @RequestParam("_limit") Optional<Integer> size){
        long status1 = status.orElse((long) 1);
        String[] a = jwt.split(" ");
        long userId = tokenProvider.getUserIdFromJWT(a[1]);
        List<OrderManagerDTO> orderManagerDTOS;
        orderManagerDTOS = cartService.findByOder(userId,status1);
        int pagesize = size.orElse(12);
        PagedListHolder<OrderManagerDTO> pagedListHolder = new PagedListHolder<>(orderManagerDTOS);
        pagedListHolder.setPageSize(pagesize);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
        }
        OrderManagerPagination managerPagination = new OrderManagerPagination();
        managerPagination.setData(pagedListHolder.getPageList());
        Pagination pagination = new Pagination();
        pagination.set_limit(pagesize);
        pagination.set_page(page);
        pagination.set_total(orderManagerDTOS.size());
        managerPagination.setPagination(pagination);
        return ResponseEntity.ok().body(managerPagination);
    }
    @GetMapping("/orderManager/status")
    public ResponseEntity getStatus(){
        return ResponseEntity.ok().body(statusService.findAll());
    }
    @PutMapping("/orderManager/{id}")
    public ResponseEntity cancelOrrder(@PathVariable("id") long id,@RequestHeader("Authorization") String jwt){
        String[] a = jwt.split(" ");
        long userId = tokenProvider.getUserIdFromJWT(a[1]);
        Cart cart =cartService.findById(id);
        if (cart.getStatus().getId()==2){
            cart.setStatus(statusService.findById(6));
            cartService.update(cart);
            return ResponseEntity.ok().body(new Message("đã hủy thành công"));
        }else {
            return ResponseEntity.badRequest().body(new Message("đơn hàng không thể hủy"));
        }
    }
}
