package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Cart;
import source.entity.CartItem;
import source.entity.Product;
import source.entity.Status;
import source.payload.CartItems;
import source.payload.Order;
import source.repository.CartRepo;
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
}
