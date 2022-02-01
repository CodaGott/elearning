package io.elearning.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourseDto {
    private String courseName;
    private String courseDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updated;
    private BigDecimal coursePrice;
}
