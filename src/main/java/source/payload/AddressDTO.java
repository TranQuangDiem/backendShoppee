package source.payload;

import lombok.Data;
import source.entity.Address;

import java.util.List;

@Data
public class AddressDTO {
    private List<Address> addressList;
}
