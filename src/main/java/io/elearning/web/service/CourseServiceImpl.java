package io.elearning.web.service;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{


    @Override
    public Course createCourse(CourseDto courseDto) {
        return null;
    }

    @Override
    public Course updateCourse(CourseDto courseDto, Long courseId) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public Optional<Course> getACourseByName(String courseName) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getACourseByCost(BigDecimal price) {
        return Optional.empty();
    }

    @Override
    public void deleteACourse(Long courseId) {

    }

    @Override
    public Course getACourseById(Long courseId) {
        return null;
    }
}
