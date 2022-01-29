package io.elearning.web.service;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course createCourse(CourseDto courseDto);
    Course updateCourse(CourseDto courseDto, Long courseId);
    List<Course> getAllCourses();
    Optional<Course> getACourseByName(String courseName);
    Optional<Course> getACourseByCost(BigDecimal price);
    void deleteACourse(Long courseId);
    Course getACourseById(Long courseId);
}
