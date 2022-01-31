package io.elearning.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name can't be blank")
    private String fullName;
    @NotBlank(message = "Username can't be blank")
    @Column(unique = true, nullable = false)
    private String userName;
    @NotNull(message = "Provide a password")
    private String password;
    @Email(message = "Please provide a valid email")
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Course> courses;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void addCourse(Course course){
        if (courses == null){
            this.courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
