package source.payload;

import lombok.Data;

@Data
public class UserEditDTO {
    private Long id;
    private String fullname;
    private String email;
    private String phone;
}
