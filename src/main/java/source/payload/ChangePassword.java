package source.payload;

import lombok.Data;

@Data
public class ChangePassword {
    private String email;
    private String password;
    private String repassword;
    private int otp;
}
