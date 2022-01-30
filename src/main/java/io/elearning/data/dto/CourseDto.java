package io.elearning.data.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDto {
    private String courseName;
    private String courseDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updated;
}
