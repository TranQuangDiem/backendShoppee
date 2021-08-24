package source.payload;

import lombok.Data;
import source.entity.Status;

import java.sql.Timestamp;
import java.util.List;
@Data
public class OrderManagerDTO {
    private long id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private long cartTotal;
    private String payment;
    private long totalBill;
    private List<CartItems> cartItems;
    private Status status;
    private Timestamp date;
}
