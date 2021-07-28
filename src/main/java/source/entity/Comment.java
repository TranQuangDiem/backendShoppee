package source.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    private long product_id;
    private int rate;
    private String content;
    private Date date;
    // mappedBy trỏ tới tên biến images ở trong product.
    @ManyToMany(mappedBy = "images")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    private List<Product> products;
    private int active;
}
