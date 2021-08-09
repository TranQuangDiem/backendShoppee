package source.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int active;
    private String status;
    private String value;
}
