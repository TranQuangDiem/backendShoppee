package source.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fullname;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @NotBlank
    private String password;
    private boolean gender;
    private String phone;
    private String address;
    @OneToOne
    @JoinColumn(name = "role")
    private Role role;
}
