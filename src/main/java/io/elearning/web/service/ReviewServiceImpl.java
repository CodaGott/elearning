package io.elearning.web.service;

import io.elearning.data.dto.ReviewDto;
import io.elearning.data.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{


    @Override
    public Review createAReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public Review updateReview(ReviewDto reviewDto, Long reviewId) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public void deleteAReview(Long reviewId) {

    }
}
