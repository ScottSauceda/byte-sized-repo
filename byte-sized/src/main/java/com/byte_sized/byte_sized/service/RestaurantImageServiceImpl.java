package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.model.RestaurantImage;
import com.byte_sized.byte_sized.repository.RestaurantImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantImageServiceImpl implements RestaurantImageService {

    @Autowired
    RestaurantImageRepository restaurantImageRepository;

    @Transactional
    public List<RestaurantImage> getRestaurantImages() throws ImageNotFoundException {
        List<RestaurantImage> restaurantImages = new ArrayList();
        if(restaurantImageRepository.findAll().isEmpty()){
            throw new ImageNotFoundException("No RestaurantImages to return");
        } else {
            List<RestaurantImage> dbRestaurantImages = restaurantImageRepository.findAll();
            for(RestaurantImage restaurantImage: dbRestaurantImages){
                restaurantImages.add(restaurantImage);
            }
            return restaurantImages;
        }
    }

    @Transactional
    public List<RestaurantImage> getRestaurantImagesByRestaurantId(Integer restaurantId) throws ImageNotFoundException, RestaurantNotFoundException {
        List<RestaurantImage> restaurantImages = new ArrayList<>();
        if(restaurantImageRepository.findAll().isEmpty()){
            throw new ImageNotFoundException("No RestaurantImages to return");
        } else if(restaurantImageRepository.findAllByRestaurantsId(restaurantId).isEmpty()){
            throw new RestaurantNotFoundException("No Images found for restaurant with id : " +  restaurantId + ". Please try again.");
        } else {
            List<RestaurantImage> dbRestaurantImages = restaurantImageRepository.findAllByRestaurantsId(restaurantId);
            for(RestaurantImage restaurantImage: dbRestaurantImages){
                restaurantImages.add(restaurantImage);
            }
            return restaurantImages;
        }
    }
}