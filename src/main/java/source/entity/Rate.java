package source.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String value;
    private String Status;
    private int sumCmt;
}
