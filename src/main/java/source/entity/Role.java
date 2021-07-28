package source.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role_name;
}
