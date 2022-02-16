package io.elearning.web.service;

import io.elearning.data.dto.ReviewDto;
import io.elearning.data.models.Course;
import io.elearning.data.models.Review;
import io.elearning.data.repository.CourseRepository;
import io.elearning.data.repository.ReviewRepository;
import io.elearning.data.repository.UserRepository;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.ReviewException;
import io.elearning.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ReviewService reviewService = new ReviewServiceImpl(modelMapper);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAReview() throws CourseException, UserException {

        Course courseToReview = new Course();
        courseToReview.setCourseName("Good course");
        Long courseId = 1L;
        Long userId = 1L;
        courseToReview.setId(courseId);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewPoint(4);
        reviewDto.setReviewDescription("This course was amazing");
        reviewDto.setCourses(courseToReview);

        Review review = new Review();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseToReview));
        reviewService.createAReview(reviewDto, courseId, userId);

        ArgumentCaptor<Review> captureReview = ArgumentCaptor.forClass(Review.class);

        verify(reviewRepository, times(1)).save(captureReview.capture());
        Review capturedReview = captureReview.getValue();

        assertThat(capturedReview.getReviewDescription()).isEqualTo(reviewDto.getReviewDescription());

    }

    @Test
    void updateReview() throws ReviewException {

        Review reviewToUpdate = new Review();
        reviewToUpdate.setReviewDescription("Good course");
        reviewToUpdate.setReviewPoint(3);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewPoint(4);
        reviewDto.setReviewDescription("This course was amazing");

        Long reviewId = 1L;

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(reviewToUpdate));
        reviewService.updateReview(reviewDto, reviewId);
        ArgumentCaptor<Review> captor = ArgumentCaptor.forClass(Review.class);

        verify(reviewRepository, times(1)).save(captor.capture());
        Review updatedReview = captor.getValue();
        assertThat(reviewToUpdate.getReviewDescription()).isEqualTo(updatedReview.getReviewDescription());
    }

    @Test
    void deleteAReview() throws ReviewException {

        Review reviewToUpdate = new Review();
        reviewToUpdate.setReviewDescription("Good course");
        reviewToUpdate.setReviewPoint(3);
        Long reviewId = 1L;

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(reviewToUpdate));
        reviewService.deleteAReview(reviewId);

        verify(reviewRepository).delete(reviewToUpdate);
//        assertThat(reviewService.deleteAReview(reviewId)).isEqualTo(false); // If the method returns boolean then we can assert
    }
}