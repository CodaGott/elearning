package io.elearning.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer reviewPoint; // can't be more than 5
    private String reviewDescription;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User users;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

}
