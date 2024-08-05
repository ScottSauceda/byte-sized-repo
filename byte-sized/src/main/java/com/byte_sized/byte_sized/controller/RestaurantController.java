package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.data.RestaurantInformation;
import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Restaurant;
import com.byte_sized.byte_sized.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping(value = "/restaurants")
    public ResponseEntity<List<RestaurantInformation>> getRestaurants() throws RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurants());
        } catch(RestaurantNotFoundException restaurantNotFoundException) {
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/owner/{ownerId}")
    public ResponseEntity<List<RestaurantInformation>> getOwnerRestaurants(@PathVariable Integer ownerId) throws UserNotFoundException, RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getOwnerRestaurants(ownerId));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(RestaurantNotFoundException restaurantNotFoundException){
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getOwnerRestaurants(ownerId));
        }
    }

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<RestaurantInformation> getRestaurantByRestaurantId(@PathVariable Integer restaurantId) throws RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantByRestaurantId(restaurantId));
        } catch(RestaurantNotFoundException restaurantNotFoundException) {
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantInformation newRestaurant) throws RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.createRestaurant(newRestaurant));
        } catch(RestaurantNotFoundException restaurantNotFoundException){
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{restaurantId}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Integer restaurantId, @RequestBody RestaurantInformation updateRestaurant) throws RestaurantNotFoundException, UserNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateRestaurant(restaurantId, updateRestaurant));
        } catch(RestaurantNotFoundException restaurantNotFoundException){
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}