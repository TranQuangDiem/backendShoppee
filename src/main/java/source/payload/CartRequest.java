package source.payload;

import lombok.Data;

import java.util.List;
@Data
public class CartRequest {
    private List<CartItems> cartItems;
}
