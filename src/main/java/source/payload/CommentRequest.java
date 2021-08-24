package source.payload;

import lombok.Data;

@Data
public class CommentRequest {
    private int rate;
    private String content;
    private long idp;
    private long idc;
}
