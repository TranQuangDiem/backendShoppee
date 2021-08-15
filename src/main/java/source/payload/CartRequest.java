package source.payload;

import lombok.Data;

@Data
public class CartRequest {
    private long idc;
    private long idp;
    private NewProduct newProduct;
    private int quantity;

}
