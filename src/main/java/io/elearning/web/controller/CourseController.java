package io.elearning.web.controller;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.repository.CourseRepository;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.UserException;
import io.elearning.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private CourseService courseService;


    @PostMapping("course/{userId}")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto, @PathVariable Long userId){
        try {
            courseService.createCourse(courseDto, userId);
            return new ResponseEntity<>("Course created", HttpStatus.CREATED);
        }
        catch (CourseException | UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
