package source.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
@Data
public class ForgotPassword {

    @Id
    private String email;
    private int otp;
    private Timestamp date;
}
