package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.RestaurantNotFoundException;
import com.byte_sized.byte_sized.model.Restaurant;
import com.byte_sized.byte_sized.model.RestaurantImage;
import com.byte_sized.byte_sized.service.RestaurantImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantImage")
public class RestaurantImageController {

    @Autowired
    RestaurantImageService restaurantImageService;

    @GetMapping(value = "/restaurantImages")
    public ResponseEntity<List<RestaurantImage>> getRestaurantImages() throws ImageNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantImageService.getRestaurantImages());
        } catch(ImageNotFoundException imageNotFoundException){
            return new ResponseEntity(imageNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/restaurantImages/{restaurantId}")
    public ResponseEntity<List<RestaurantImage>> getRestaurantImagesByRestaurantId(@PathVariable Integer restaurantId) throws ImageNotFoundException, RestaurantNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantImageService.getRestaurantImagesByRestaurantId(restaurantId));
        } catch(ImageNotFoundException imageNotFoundException){
            return new ResponseEntity(imageNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(RestaurantNotFoundException restaurantNotFoundException){
            return new ResponseEntity(restaurantNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}