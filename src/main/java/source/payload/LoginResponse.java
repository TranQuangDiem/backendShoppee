package source.payload;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserReponse user;

    public LoginResponse(String accessToken, UserReponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
