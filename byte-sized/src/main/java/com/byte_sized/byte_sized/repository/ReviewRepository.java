package com.byte_sized.byte_sized.repository;

import com.byte_sized.byte_sized.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByReviewUserId(Integer userId);

    List<Review> findAllByReviewRestaurantId(Integer restaurantId);
}