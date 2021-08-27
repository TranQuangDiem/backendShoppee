package source.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import source.vnpay.common.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @PostMapping("/thanhtoan")
    public ResponseEntity doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "thanh toán đơn hàng";
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;

        int amount = 50000 * 100;
        Map vnp_Params = new HashMap();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);

        String locate = "vn";
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        //Billing
//        vnp_Params.put("vnp_Bill_Mobile", "0355541981");
//        vnp_Params.put("vnp_Bill_Email", "trandiem1006@gmail.com");
//        String fullName = ("tran quang diem").trim();
//        if (fullName != null && !fullName.isEmpty()) {
//            int idx = fullName.indexOf(' ');
//            String firstName = fullName.substring(0, idx);
//            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
//            vnp_Params.put("vnp_Bill_FirstName", firstName);
//            vnp_Params.put("vnp_Bill_LastName", lastName);
//
//        }
//        vnp_Params.put("vnp_Bill_Address", "tp hcm");
//        vnp_Params.put("vnp_Bill_City", "hcm");
////        vnp_Params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
//        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
//            vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
//        }
        // Invoice
//        vnp_Params.put("vnp_Inv_Phone", "0355541981");
//        vnp_Params.put("vnp_Inv_Email", "trandiem1006@");
//        vnp_Params.put("vnp_Inv_Customer", "diem tran");
//        vnp_Params.put("vnp_Inv_Address", "tp ho chi minh");
//        vnp_Params.put("vnp_Inv_Company", "abc");
//        vnp_Params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
//        vnp_Params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
//        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        String vnp_SecureHash = "3e0d61a0c0534b2e36680b3f7277743e8784cc4e1d68fa7d276e79c23be7d6318d338b477910a27992f5057bb1582bd44bd82ae8009ffaf6d141219218625c42";
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
        return ResponseEntity.ok().body(paymentUrl);
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
    public ResponseEntity cancelOrrder(@PathVariable("id") long id,@RequestHeader("Authorization") String jwt, @RequestParam("status")Optional<Long> status,@RequestParam(required = false, value = "_page") Integer page,
                                       @RequestParam("_limit") Optional<Integer> size){
        String[] a = jwt.split(" ");
        long userId = tokenProvider.getUserIdFromJWT(a[1]);
        long status1 = status.orElse((long) 1);
        Cart cart =cartService.findById(id);
        if (cart.getStatus().getId()==2){
            cart.setStatus(statusService.findById(6));
            cartService.update(cart);
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
        }else {
            return ResponseEntity.badRequest().body(new Message("đơn hàng không thể hủy"));
        }
    }
}
