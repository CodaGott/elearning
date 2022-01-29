package io.elearning.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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
    private String userName;
    @Email(message = "Please provide a valid email")
    private String email;
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Enumerated(EnumType.STRING)
    private Role role;
}
