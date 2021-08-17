package source.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    private String address;
    private boolean status;
    private long user;

}
