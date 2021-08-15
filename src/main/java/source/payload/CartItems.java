package source.payload;

import lombok.Data;

@Data
public class CartItems {
    private long idc;
    private long idp;
    private NewProduct newProduct;
    private int quantity;

}
