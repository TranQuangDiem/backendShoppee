package source.payload;

import lombok.Data;
import source.entity.Color;

import java.util.Date;
@Data
public class CommentDTO {
    private long id;
    private String userName;
    private int rate;
    private String content;
    private Date date;
    private Color color;
    private int active;
}
