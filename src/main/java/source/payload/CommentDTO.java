package source.payload;

import lombok.Data;

import java.util.Date;
@Data
public class CommentDTO {
    private long id;
    private UserReponse user;
    private long idproduct;
    private int rate;
    private String content;
    private Date date;
    private int active;
}
