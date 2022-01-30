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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public Review createAReview(ReviewDto reviewDto, Long courseId, Long userId) throws UserException, CourseException {

//        User userWithReview = userRepository.findById(userId).orElseThrow(
//                () -> new UserException("User with id does not exist"));

        Course courseToReview = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseException("Course with id does not exist"));

        Review review = new Review();

        modelMapper.map(reviewDto, review);
        courseToReview.addReview(review);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(ReviewDto reviewDto, Long reviewId) throws ReviewException {
        Review reviewToUpdate = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ReviewException("The review you want to update does not exist"));
        modelMapper.map(reviewDto, reviewToUpdate);
        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void deleteAReview(Long reviewId) throws ReviewException {
        Review reviewToDelete = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ReviewException("The review with id does not exist"));
        reviewRepository.delete(reviewToDelete);
    }
}
