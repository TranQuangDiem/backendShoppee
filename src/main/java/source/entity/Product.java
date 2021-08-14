package source.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int quantitySold;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Quan hệ n-n với đối tượng ở dưới (product)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "product_image", //Tạo ra một join Table tên
            joinColumns = @JoinColumn(name = "product_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại
            inverseJoinColumns = @JoinColumn(name = "image_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
    )
    private List<Image> images;
    private double rate;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//    @ToString.Exclude // Khoonhg sử dụng trong toString()
//    @JoinTable(name = "product_comment", //Tạo ra một join Table tên
//            joinColumns = @JoinColumn(name = "product_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại
//            inverseJoinColumns = @JoinColumn(name = "comment_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
//    )
//    private List<Comment> comments;
    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String status;
    private Date createDate;
    private double sale;
    private double salePrice;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "product_color", //Tạo ra một join Table tên
            joinColumns = @JoinColumn(name = "product_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại
            inverseJoinColumns = @JoinColumn(name = "color_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới
    )
    private List<Color> colors;
    private String type;
    private int active;

}
