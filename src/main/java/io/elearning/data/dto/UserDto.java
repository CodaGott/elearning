package io.elearning.data.dto;

import io.elearning.data.models.Course;
import io.elearning.data.models.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class UserDto {
    private String fullName;
    private String userName;
    private String email;
    private LocalDate dateOfBirth;
    private List<Course> courses;
    private Role role;
}
