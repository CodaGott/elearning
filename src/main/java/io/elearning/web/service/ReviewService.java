package io.elearning.web.service;

import io.elearning.data.dto.ReviewDto;
import io.elearning.data.models.Review;
import io.elearning.exceptions.CourseException;
import io.elearning.exceptions.ReviewException;
import io.elearning.exceptions.UserException;

import java.util.List;

public interface ReviewService {
    Review createAReview(ReviewDto reviewDto, Long courseId, Long userId) throws UserException, CourseException;
    Review updateReview(ReviewDto reviewDto, Long reviewId) throws ReviewException;
    List<Review> getAllReviews();
    void deleteAReview(Long reviewId) throws ReviewException;
}
