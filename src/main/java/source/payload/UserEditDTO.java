package source.payload;

import lombok.Data;
import source.entity.Role;

@Data
public class UserEditDTO {
    private Long id;
    private String fullname;
    private String email;
    private boolean gender;
    private String phone;
    private String address;
    private Role role;
}
