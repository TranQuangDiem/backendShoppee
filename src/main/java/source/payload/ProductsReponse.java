package source.payload;

import lombok.Data;
import source.entity.Product;

import java.util.List;
@Data
public class ProductsReponse {
    List<Product> products;
    Pagination pagination;
    String  title_like;
}
