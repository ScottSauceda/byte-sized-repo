package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.exception.ReviewNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Review;
import com.byte_sized.byte_sized.model.User;
import com.byte_sized.byte_sized.repository.RestaurantRepository;
import com.byte_sized.byte_sized.repository.ReviewRepository;
import com.byte_sized.byte_sized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Review> getReviews() throws ReviewNotFoundException {
        List<Review> reviews = new ArrayList();
        if(reviewRepository.findAll().isEmpty()){
            throw new ReviewNotFoundException("No Reviews to return");
        } else {
            List<Review> dbReviews = reviewRepository.findAll();
            for(Review review: dbReviews){
                reviews.add(review);
            }
            return reviews;
        }
    }

    @Transactional
    public List<Review> getUserReviews(Integer userId) throws UserNotFoundException, ReviewNotFoundException {
        List<Review> reviews = new ArrayList();
        if(reviewRepository.findAll().isEmpty()){
            throw new ReviewNotFoundException("No Reviews to return");
        } else if(reviewRepository.findAllByReviewUserId(userId).isEmpty()) {
            throw new UserNotFoundException("No Reviews for that user to return");
        } else {
            List<Review> dbReviews = reviewRepository.findAllByReviewUserId(userId);
            for(Review review: dbReviews){
                reviews.add(review);
            }
            return reviews;
        }
    }

    @Transactional
    public List<Review> getRestaurantReviews(Integer restaurantId) throws RestaurantNotFoundException, ReviewNotFoundException {
        List<Review> reviews = new ArrayList();
        if(reviewRepository.findAll().isEmpty()){
            throw new ReviewNotFoundException("No Reviews to return.");
        } else if(reviewRepository.findAllByReviewRestaurantId(restaurantId).isEmpty()){
            throw new RestaurantNotFoundException("No Review for that Restaurant to return");
        } else {
            List<Review> dbReviews = reviewRepository.findAllByReviewRestaurantId(restaurantId);
            for(Review review: dbReviews){
                reviews.add(review);
            }
            return reviews;
        }
    }

    @Transactional
    public Optional<Review> getReviewById(Integer reviewId) throws ReviewNotFoundException {
        Optional<Review> review = null;
        if(reviewRepository.findById(reviewId).isEmpty()){
            throw new ReviewNotFoundException("Review with reviewId: " + reviewId + " does not exists. Please try again.");
        } else {
            review = reviewRepository.findById(reviewId);
        }
        return review;
    }

    @Transactional
    public String createReview(Review newReview, Integer restaurantId) throws UserNotFoundException, ReviewNotFoundException, RestaurantNotFoundException {

        // find user from usersId in newReview
        User newReviewUser = userRepository.findTopByUserId(newReview.getReviewUserId()).orElse(null);

        // Review
        Review savedReview = new Review();

        // RestaurantReview
        Review savedRestaurantReview = new Review();

        Review tempReview = new Review();
        tempReview.setReviewRestaurantId(restaurantId);

        if(newReviewUser == null){
            throw new UserNotFoundException("User not found. Could not create review");
        } else {
            tempReview.setReviewUserId(newReviewUser.getUserId());
            System.out.println("newReview userId");
            System.out.println(tempReview.getReviewUserId());
        }

        if(restaurantRepository.findTopByRestaurantId(restaurantId) == null){
            throw new RestaurantNotFoundException("Restaurant with Id " + restaurantId + " not found. Could no create review.");
        }

        System.out.println("newReview");
        System.out.println(newReview);

        savedReview = reviewRepository.saveAndFlush(newReview);


        if(savedReview.getReviewId() == null){
            throw new ReviewNotFoundException("Review not assigned. Please try again.");
        } else {
            return "New Review created with Id: " + savedReview.getReviewId() + " assigned to restaurant: " + savedReview.getReviewRestaurantId() ;
        }
    }

    @Transactional
    public String updateReview(Review updateReview, Integer reviewId) throws ReviewNotFoundException {
        Review dbReview = reviewRepository.findById(reviewId).orElse(null);

        if(dbReview == null){
            throw new ReviewNotFoundException("Review with Id: " + reviewId + " does not exists. Please try again.");
        } else {
            // review title not here, making a choice to not allow the title to be changed, can add later if change mind
            dbReview.setReviewRating(updateReview.getReviewRating());
            dbReview.setReviewText(updateReview.getReviewText());
            return "Review has been updated successfully";
        }
    }

    @Transactional
    public String deleteReview(Integer reviewId) throws ReviewNotFoundException {
        Review dbReview = reviewRepository.findById(reviewId).orElse(null);

        if(reviewRepository.findById(reviewId) == null){
            throw new ReviewNotFoundException("Review with Id: " + reviewId + " does not exists. Please try again.");
        } else {
            reviewRepository.delete(dbReview);
            return "Review has been deleted successfully";
        }
    }
}