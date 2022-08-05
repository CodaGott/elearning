package io.elearning.web.service;

import io.elearning.data.dto.CourseDto;
import io.elearning.data.models.Course;
import io.elearning.data.models.Role;
import io.elearning.data.models.User;
import io.elearning.data.repository.CourseRepository;
import io.elearning.data.repository.ReviewRepository;
import io.elearning.data.repository.UserRepository;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ReviewRepository reviewRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private CourseService courseService = new CourseServiceImpl(modelMapper);

    User testUser;

    @BeforeEach
    void setUp(){
        testUser = new User();
        testUser.setUserName("Name");
        testUser.setRole(Role.TEACHER);
    }


    @Test
    void courseCanBeCreated() throws CourseException, UserException {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("My Java Course");
        courseDto.setCourseDescription("Learn Java from me");
        courseDto.setCoursePrice(BigDecimal.valueOf(199.00));

        Long userId = 1L;


        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        courseService.createCourse(courseDto, userId);
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);


        verify(courseRepository, times(1)).save(courseArgumentCaptor.capture());
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        Course course = courseArgumentCaptor.getValue();
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getCourses()).isNotEmpty();
        assertThat(course.getCourseName()).isEqualTo(courseDto.getCourseName());
    }

    @Test
    void courseInfoCanBeUpdated() throws CourseException {

        Course course = new Course();
        course.setCourseName("Python Course");
        course.setCourseDescription("This is a python course");
        courseRepository.save(course);

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("My Java Course");
        courseDto.setCourseDescription("Learn Java from me");

        Long courseId = 1L;

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        courseService.updateCourse(courseDto, courseId);
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository, times(2)).save(courseArgumentCaptor.capture());
        Course updatedCourse = courseArgumentCaptor.getValue();

        assertThat(course.getCourseName()).isEqualTo(updatedCourse.getCourseName());
    }

    @Test
    void getAllCourse(){
        List<Course> courses = new ArrayList<>();

        Course course1 = new Course();
        Course course2 = new Course();
        Course course3 = new Course();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courseRepository.saveAll(courses);

        when(courseRepository.findAll()).thenReturn(courses);

        assertEquals(3, courseService.getAllCourses().size());

    }

    @Test
    void courseInfoCanBeDeleted() throws CourseException {
        Course course = new Course();
        String courseName = "Course Name";
        course.setCourseName(courseName);
        Long courseId = 1L;

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        courseService.getACourseById(courseId);

        assertThat(course.getCourseName()).isEqualTo("Course Name");
    }

    @Test
    void courseCanBeFoundUsingName() throws CourseException {
        String name = "Course Name";
        Course course = new Course();
        course.setCourseName(name);
        Course course2 = new Course();
        course2.setCourseName("Course 2");
        Course course3 = new Course();
        course3.setCourseName("Course 3");

        when(courseRepository.findAllByCourseName(name)).thenReturn(Optional.of(course));


        assertThat(courseService.getACourseByName(name)).isEqualTo(course);
    }

    @Test
    void courseCanBeDeleted() throws CourseException {
        Course course = new Course();
        String courseName = "Course Name";
        course.setCourseName(courseName);
        Long courseId = 1L;

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        courseService.deleteACourse(courseId);

        verify(courseRepository).delete(course);
    }

    @Test
    void canFindCourseUsingPrice(){
        Course course = new Course();
        String courseName = "Course Name";
        course.setCourseName(courseName);
        double bigDecimal = 600.00;
        course.setCoursePrice(bigDecimal);
        Long courseId = 1L;
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseRepository.findAllByCoursePrice(bigDecimal)).thenReturn(courses);

        assertThat(courseService.getCoursesByPrice(bigDecimal)).isEqualTo(Arrays.asList(course));
    }

}