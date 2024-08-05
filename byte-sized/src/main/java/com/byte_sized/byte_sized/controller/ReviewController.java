package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.exception.ReviewNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Review;
import com.byte_sized.byte_sized.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/reviews")
    public ResponseEntity<List<Review>> getReviews() throws ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviews());
        } catch(ReviewNotFoundException reviewNotFoundException) {
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Integer userId) throws UserNotFoundException, ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getUserReviews(userId));
        } catch(UserNotFoundException userNotFoundException) {
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(ReviewNotFoundException reviewNotFoundException) {
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/restaurant/{restaurantId}")
    public ResponseEntity<List<Review>> getRestaurantReviews(@PathVariable Integer restaurantId) throws RestaurantNotFoundException, ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getRestaurantReviews(restaurantId));
        } catch(RestaurantNotFoundException restaurantNotFoundException) {
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(ReviewNotFoundException reviewNotFoundException) {
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{reviewId}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable Integer reviewId) throws ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewById(reviewId));
        } catch(ReviewNotFoundException reviewNotFoundException){
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create/{restaurantId}")
    public ResponseEntity<String> createReview(@RequestBody Review newReview, @PathVariable Integer restaurantId) throws UserNotFoundException, ReviewNotFoundException, RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.createReview(newReview, restaurantId));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(ReviewNotFoundException reviewNotFoundException){
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(RestaurantNotFoundException restaurantNotFoundException){
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Integer reviewId, @RequestBody Review updateReview) throws ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(updateReview, reviewId));
        } catch(ReviewNotFoundException reviewNotFoundException){
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) throws ReviewNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.deleteReview(reviewId));
        } catch(ReviewNotFoundException reviewNotFoundException){
            return new ResponseEntity(reviewNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}