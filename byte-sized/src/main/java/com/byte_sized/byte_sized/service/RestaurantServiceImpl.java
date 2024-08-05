package com.byte_sized.byte_sized.service;


import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Location;
import com.byte_sized.byte_sized.model.Restaurant;
import com.byte_sized.byte_sized.data.RestaurantInformation;
import com.byte_sized.byte_sized.model.User;
import com.byte_sized.byte_sized.repository.LocationRepository;
import com.byte_sized.byte_sized.repository.RestaurantRepository;
import com.byte_sized.byte_sized.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public List<RestaurantInformation> getRestaurants() throws RestaurantNotFoundException {
        List<RestaurantInformation> restaurants = new ArrayList();
        if(restaurantRepository.findAll().isEmpty()){
            throw new RestaurantNotFoundException("No Restaurants to return");
        } else {
            List<Restaurant> dbRestaurants = restaurantRepository.findAll();
            for(Restaurant restaurant: dbRestaurants){
                restaurants.add(getRestaurantInformation(restaurant.getRestaurantId()));
            }
            return restaurants;
        }
    }

    @Transactional
    public List<RestaurantInformation> getOwnerRestaurants(Integer ownerId) throws UserNotFoundException, RestaurantNotFoundException {
        List<RestaurantInformation> restaurants = new ArrayList();
        if(restaurantRepository.findAll().isEmpty()){
            throw new RestaurantNotFoundException("No Restaurants to return");
        } else if(restaurantRepository.findAllByOwnerUserId(ownerId).isEmpty()) {
            throw new UserNotFoundException("No restaurants to return for owner with ID : " + ownerId + ".");
        } else {
            List<Restaurant> dbRestaurants = restaurantRepository.findAllByOwnerUserId(ownerId);
            for(Restaurant restaurant: dbRestaurants){
                restaurants.add(getRestaurantInformation(restaurant.getRestaurantId()));
            }
            return restaurants;
        }
    }

    @Transactional
    public RestaurantInformation getRestaurantByRestaurantId(Integer restaurantId) throws RestaurantNotFoundException {
        RestaurantInformation restaurant = null;
        if(restaurantRepository.findTopByRestaurantId(restaurantId).isEmpty()){
            throw new RestaurantNotFoundException("Restaurant with Id: " + restaurantId + " does not exists. Please try again.");
        } else {
            restaurant = getRestaurantInformation(restaurantId);
        }
        return restaurant;
    }

    @Transactional
    public String createRestaurant(RestaurantInformation newRestaurant) throws UserNotFoundException {
        Restaurant savedRestaurant = new Restaurant();
        Location savedLocation = new Location();
        Optional<User> foundOwner = null;

        System.out.println("newRestaurant");
        System.out.println(newRestaurant);

        if(newRestaurant == null){
            System.out.println("new restaurant is null");
        }


        System.out.println("new restaurant owner id");
        System.out.println(newRestaurant.getOwnerId());

        if(userRepository.findTopByUserId(newRestaurant.getOwnerId()).isEmpty()){
            throw new UserNotFoundException("Something went wrong. Please try again. U");
        } else {
            foundOwner = userRepository.findTopByUserId(newRestaurant.getOwnerId());

            if(foundOwner.isPresent()){
                User owner = foundOwner.get();
                savedRestaurant.setOwner(owner);
            }
        }

        savedLocation.setLocationName(newRestaurant.getLocation_name());
        savedLocation.setAddress (newRestaurant.getAddress());
        savedLocation.setCity (newRestaurant.getCity());
        savedLocation.setState (newRestaurant.getState());
        savedLocation.setZipCode (newRestaurant.getZipCode());
        savedLocation = locationRepository.saveAndFlush(savedLocation);
        if(locationRepository.findTopByLocationId(savedLocation.getLocationId()).isEmpty()){
            return "Something went wrong. Please try again. L";
        } else {
            System.out.println("Location created with Id: " + savedLocation.getLocationId());
        }

        savedRestaurant.setRestaurantName(newRestaurant.getRestaurantName());
        savedRestaurant.setRestaurantPhone(newRestaurant.getRestaurantPhone());
        savedRestaurant.setLocation(savedLocation);
        savedRestaurant = restaurantRepository.saveAndFlush(savedRestaurant);

        if(restaurantRepository.findTopByRestaurantId(savedRestaurant.getRestaurantId()).isEmpty()){
            return "Something went wrong. Please try again. R";
        } else {
            return "Restaurant " + newRestaurant.getRestaurantName() + " created with restaurant id: " + savedRestaurant.getRestaurantId();
        }
    }

    @Transactional
    public String updateRestaurant(Integer restaurantId, RestaurantInformation updateRestaurant) throws RestaurantNotFoundException {
        return "hi";
    }

    @Transactional
    public String deleteRestaurant(Integer restaurantId) throws RestaurantNotFoundException {
        return "hi";
    }

    @Transactional
    public RestaurantInformation getRestaurantInformation(Integer restaurantId){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        Restaurant restaurant = optionalRestaurant.get();
        RestaurantInformation restaurantInformation = new RestaurantInformation();

        // set restaurant information from restaurant
        restaurantInformation.setRestaurantId(restaurant.getRestaurantId());
        restaurantInformation.setRestaurantName(restaurant.getRestaurantName());
        restaurantInformation.setRestaurantPhone(restaurant.getRestaurantPhone());

        // set restaurant information from owner
        restaurantInformation.setOwnerId(restaurant.getOwner().getUserId());
        restaurantInformation.setOwnerName(restaurant.getOwner().getUserName());

        // set restaurant information from location
        restaurantInformation.setLocationId(restaurant.getLocation().getLocationId());
        restaurantInformation.setLocation_name(restaurant.getLocation().getLocationName());
        restaurantInformation.setAddress(restaurant.getLocation().getAddress());
        restaurantInformation.setCity(restaurant.getLocation().getCity());
        restaurantInformation.setState(restaurant.getLocation().getState());
        restaurantInformation.setZipCode(restaurant.getLocation().getZipCode());

        // set restaurant reviews

        // set restaurant images

        return restaurantInformation;
    }

}