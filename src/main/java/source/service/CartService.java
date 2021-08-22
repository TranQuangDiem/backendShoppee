package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.*;
import source.payload.*;
import source.repository.CartRepo;
import source.repository.CommentRepository;
import source.repository.StatusRepo;
import source.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ModelMapper mapper;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepo;
    public void save (Order order,long userId){
        List<CartItem> cartItems = new ArrayList<CartItem>();
        for (CartItems cartItems1:order.getCartItems()) {
            Product product =mapper.map(cartItems1.getNewProduct(), Product.class);
            CartItem c =mapper.map(cartItems1, CartItem.class);
            c.setProduct(product);
            c.setActive(0);
            cartItems.add(c);
        }
        Cart cart =mapper.map(order, Cart.class);
        cart.setCartItems(cartItems);
        cart.setStatus(statusRepo.findById(2));
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        Timestamp datetime = new Timestamp(date.getTime());
        cart.setDate(datetime);
        cart.setUser(userRepository.findById(userId));
        cartRepo.save(cart);

    }
    public List<OrderManagerDTO> findByOder(long userId,long status){

        List<OrderManagerDTO> orderManagerDTOS = new ArrayList<OrderManagerDTO>();
        List<Cart> carts = cartRepo.findByUser_Id(userId);
        if(status!=1) {
            carts = cartRepo.findByUser_IdAndStatus_Id(userId, status);
        }
        for (Cart cart: carts) {
            OrderManagerDTO orderManagerDTO = mapper.map(cart,OrderManagerDTO.class);
            List<CartItems> cartItems = new ArrayList<CartItems>();
            for (CartItem cartItem:cart.getCartItems()) {
                NewProduct newProduct =mapper.map(cartItem.getProduct(), NewProduct.class);
                CartItems c =mapper.map(cartItem, CartItems.class);

                List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
                for (Comment comment: commentRepo.findByIdproductAndUser_Id(newProduct.getId(),userId)) {
                    CommentDTO commentDTO = mapper.map(comment,CommentDTO.class);
                    commentDTO.setUserName(comment.getUser().getFullname());
                    commentDTOList.add(commentDTO);
                }
                newProduct.setComment(commentDTOList);
                c.setNewProduct(newProduct);
                cartItems.add(c);
            }
            orderManagerDTO.setCartItems(cartItems);
            orderManagerDTOS.add(orderManagerDTO);
        }
        return orderManagerDTOS;
    }
    public Cart findById(long id){
        return cartRepo.findById(id);
    }
    public void update(Cart cart){
        cartRepo.save(cart);
    }
}
