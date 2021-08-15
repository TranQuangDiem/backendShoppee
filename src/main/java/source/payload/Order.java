package source.payload;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private long cartTotal;
    private String payment;
    private long totalBill;
    private List<CartItems> cartItems;


}
