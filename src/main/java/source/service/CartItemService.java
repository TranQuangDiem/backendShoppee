package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Cart;
import source.entity.CartItem;
import source.payload.CartRequest;
import source.payload.NewProduct;
import source.repository.CartItemRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    ModelMapper mapper;

    public void save(CartItem cartItem){
        cartItemRepo.save(cartItem);
    }
    public List<CartRequest> findByUserId(long userId){
        List<CartRequest> cartRequests = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepo.findByUserAndActive(userId,1);
        for (CartItem cartItem:cartItems) {
            CartRequest cartRequest =mapper.map(cartItem, CartRequest.class);
            NewProduct newProduct =mapper.map(cartItem.getProduct(), NewProduct.class);
            cartRequest.setNewProduct(newProduct);
            cartRequest.setIdp(newProduct.getId());
            cartRequests.add(cartRequest);
            cartItemRepo.delete(cartItem);
        }
        return cartRequests;
    }
}
