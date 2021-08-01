package source.payload;

import lombok.Data;
import source.entity.Product;

import java.util.List;
@Data
public class ProductsReponse {
    List<Product> products;
    int limit;
    int page;
    int total;
}
