package source.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private boolean gender;
    @OneToOne
    @JoinColumn(name = "role")
    private Role role;
}
