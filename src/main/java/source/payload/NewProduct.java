package source.payload;

import lombok.Data;
import source.entity.Brand;
import source.entity.Color;
import source.entity.Image;

import java.util.Date;
import java.util.List;

@Data
public class NewProduct {
    private long id;
    private String name;
    private double price;
    private int quantitySold;
    private List<Image> images;
    private double rate;
    private Brand brand;
    private String status;
    private Date createDate;
    private double sale;
    private double salePrice;
    private List<Color> colors;
    private String type;
    private List<CommentDTO> comment;
    private int active;

}
