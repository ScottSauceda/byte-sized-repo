package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public List<Review> getReviews();

    public List<Review> getUserReviews(Integer userId);

    public List<Review> getRestaurantReviews(Integer restaurantId);

   public Optional<Review> getReviewById(Integer reviewId);

    public String createReview(Review newReview, Integer restaurantId);

    public String updateReview(Review updateReview, Integer reviewId);

    public String deleteReview(Integer reviewId);
}