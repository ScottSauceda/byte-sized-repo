package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.model.RestaurantImage;

import java.util.List;

public interface RestaurantImageService {
    public List<RestaurantImage> getRestaurantImages();

    public List<RestaurantImage> getRestaurantImagesByRestaurantId(Integer restaurantId);

    // assignImageToRestaurant

    // deleteRestaurantImage
}