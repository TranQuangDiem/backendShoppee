package source.payload;

import lombok.Data;
import source.entity.Role;
@Data
public class UserReponse {
    private Long id;
    private String fullname;
    private String avatar;
    private String email;
    private boolean gender;
    private Role role;
}
