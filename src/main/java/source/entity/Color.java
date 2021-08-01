package source.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String colorName;
//    // mappedBy trỏ tới tên biến images ở trong product.
//    @ManyToMany(mappedBy = "images")
//    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
//    @EqualsAndHashCode.Exclude
//    private List<Product> products;
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
@ToString.Exclude // Khoonhg sử dụng trong toString()
@JoinTable(name = "color_size", //Tạo ra một join Table tên
        joinColumns = @JoinColumn(name = "color_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại
        inverseJoinColumns = @JoinColumn(name = "size_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
)
private List<Size> sizes;
}
