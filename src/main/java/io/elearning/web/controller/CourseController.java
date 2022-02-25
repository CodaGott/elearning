package io.elearning.web.controller;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;
import io.elearning.data.repository.CourseRepository;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.UserException;
import io.elearning.web.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController("/course/")
@Slf4j
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private CourseService courseService;


    @PostMapping("course/{userId}")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto, @PathVariable Long userId){
        try {
            courseService.createCourse(courseDto, userId);
            log.info("Course Created for user with ID: " + userId);
            return new ResponseEntity<>("Course created", HttpStatus.CREATED);
        }
        catch (CourseException | UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/course/{courseName}")
    public ResponseEntity<?> getACourseByName(@PathVariable String courseName){
        try {
            Course course = courseService.getACourseByName(courseName);
            return new ResponseEntity<>("Found course" + course, HttpStatus.FOUND);
        }
        catch (CourseException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-course/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDto courseDto, @PathVariable Long courseId){
        try {
            courseService.updateCourse(courseDto,courseId);
            return new ResponseEntity<>("Course Updated successfully", HttpStatus.OK);
        }
        catch (CourseException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<?> getAllCourse(){
        courseService.getAllCourses();
        return new ResponseEntity<>("All Courses returned", HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteACourse(@PathVariable Long courseId){
        try {
            courseService.deleteACourse(courseId);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        catch (CourseException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId){
        try {
            courseService.getACourseById(courseId);

            return new ResponseEntity<>("Course found", HttpStatus.FOUND);
        }
        catch (CourseException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course/{coursePrice}")
    public ResponseEntity<?> getCourseByPrice(@PathVariable BigDecimal coursePrice) throws CourseException {
        courseService.getCoursesByPrice(coursePrice);
        return new ResponseEntity<>("Found course by price", HttpStatus.FOUND);
    }
}
