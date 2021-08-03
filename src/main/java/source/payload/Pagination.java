package source.payload;

import lombok.Data;

@Data
public class Pagination {
    int _page;
    int _limit;
    int _total;
}
