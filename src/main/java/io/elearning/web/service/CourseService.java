package io.elearning.web.service;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.UserException;

import java.util.List;

public interface CourseService {

    Course createCourse(CourseDto courseDto, Long userId) throws UserException, CourseException;
    Course updateCourse(CourseDto courseDto, Long courseId) throws CourseException;
    List<Course> getAllCourses();
    Course getACourseByName(String courseName) throws CourseException;
    List<Course> getCoursesByPrice(double price);
    void deleteACourse(Long courseId) throws CourseException;
    Course getACourseById(Long courseId) throws CourseException;
}
