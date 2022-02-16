package io.elearning.data.dto;

import io.elearning.data.models.Course;
import io.elearning.data.models.User;
import lombok.Data;
@Data
public class ReviewDto {
    private Integer reviewPoint; // can't be more than 5
    private String reviewDescription;
    private User users;
    private Course courses;
}
