package source.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class RateProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int value;
}
