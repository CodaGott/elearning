package io.elearning.web.service;

import io.elearning.data.dto.ReviewDto;
import io.elearning.data.models.Review;

import java.util.List;

public interface ReviewService {
    Review createAReview(ReviewDto reviewDto);
    Review updateReview(ReviewDto reviewDto, Long reviewId);
    List<Review> getAllReviews();
    void deleteAReview(Long reviewId);
}
