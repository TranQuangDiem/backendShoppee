package source.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private long cartTotal;
    private String payment;
    private long totalBill;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "cart_cartItem", //Tạo ra một join Table tên
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cartItem_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
    )
    private List<CartItem> cartItems;
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;
    private Timestamp date;
}
