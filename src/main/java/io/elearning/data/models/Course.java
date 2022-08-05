package io.elearning.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Course must have a name")
    private String courseName;
    private String courseImageUrl;
    @NotBlank(message = "Course must have a description")
    private String courseDescription;
    @NotNull(message = "Course must have a price")
    private double coursePrice;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updated;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_id")
    private Long user;

    public void addReview(Review review){
        if (reviews == null){
            this.reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

//    public void addCourseToUser(User courseOwner){
//        if (user == null){
//            this.user = courseOwner;
//        }
//    }

}
