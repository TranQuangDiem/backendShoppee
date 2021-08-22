package source.payload;

import lombok.Data;

import java.util.List;

@Data
public class OrderManagerPagination {
    List<OrderManagerDTO> data;
    Pagination pagination;
}
