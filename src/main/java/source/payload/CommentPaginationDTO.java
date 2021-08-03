package source.payload;

import lombok.Data;

import java.util.List;

@Data
public class CommentPaginationDTO {
    List<CommentDTO> comments;
    Pagination pagination;
}
