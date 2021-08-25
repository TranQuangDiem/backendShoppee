package source.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
}
