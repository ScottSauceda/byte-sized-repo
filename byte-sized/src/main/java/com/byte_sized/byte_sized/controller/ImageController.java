package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.exception.ImageNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Image;
import com.byte_sized.byte_sized.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping(value = "/images")
    public ResponseEntity<List<Image>> getImages() throws ImageNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(imageService.getImages());
        } catch(ImageNotFoundException imageNotFoundException) {
            return new ResponseEntity(imageNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/images/{userId}")
    public ResponseEntity<List<Image>> getUserImages(@PathVariable Integer userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(imageService.getUserImages(userId));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(ImageNotFoundException imageNotFoundException){
            return new ResponseEntity(imageNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}