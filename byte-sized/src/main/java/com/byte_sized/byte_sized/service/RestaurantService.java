package com.byte_sized.byte_sized.service;

import com.byte_sized.byte_sized.data.RestaurantInformation;
import com.byte_sized.byte_sized.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    public List<RestaurantInformation> getRestaurants();

    public List<RestaurantInformation> getOwnerRestaurants(Integer ownerId);

    public RestaurantInformation getRestaurantByRestaurantId(Integer restaurantId);

    public String createRestaurant(RestaurantInformation newRestaurant);

    public String updateRestaurant(Integer restaurantId, RestaurantInformation updateRestaurant);

    public String deleteRestaurant(Integer restaurantId);
}