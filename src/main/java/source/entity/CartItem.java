package source.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private int active;
    private long user;
    private long idc;
}
