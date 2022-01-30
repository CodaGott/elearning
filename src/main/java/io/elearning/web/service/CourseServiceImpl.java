package io.elearning.web.service;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;
import io.elearning.data.models.User;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.UserException;
import io.elearning.data.repository.CourseRepository;
import io.elearning.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Course createCourse(CourseDto courseDto, Long userId) throws UserException {
        User courseOwner = userRepository.findById(userId).orElseThrow(() ->
                new UserException("This user does not exist"));
        Course course = new Course();

        modelMapper.map(courseDto, course);
        courseOwner.addCourse(course);
        log.info("Saving course -> {}", course);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(CourseDto courseDto, Long courseId) throws CourseException {

        Course courseToUpdate = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseException("Course with provided Id does not exist"));
        modelMapper.map(courseDto, courseToUpdate);
        log.info("Updating course -> {}", courseToUpdate);
        return courseRepository.save(courseToUpdate);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getACourseByName(String courseName) throws CourseException {
        return courseRepository.findAllByCourseName(courseName).orElseThrow(
                () -> new CourseException("Course with given -> {} does not exist" + courseName));
    }

    @Override
    public List<Course> getCoursesByPrice(BigDecimal price) {
        return courseRepository.findAllByCoursePrice(price);
    }

    @Override
    public void deleteACourse(Long courseId) throws CourseException {
        Course courseToDelete = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseException("Course with given id does not exist"));
        log.info("Deleting course -> {}", courseToDelete);
        courseRepository.delete(courseToDelete);
    }

    @Override
    public Course getACourseById(Long courseId) throws CourseException {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new CourseException("Course with given id does not exist")
        );
    }
}
