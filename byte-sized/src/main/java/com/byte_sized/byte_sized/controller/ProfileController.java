package com.byte_sized.byte_sized.controller;


import com.byte_sized.byte_sized.exception.ProfileNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Profile;
import com.byte_sized.byte_sized.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getProfiles() throws ProfileNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.getUserProfiles());
        } catch(ProfileNotFoundException userProfileNotFoundException){
            return new ResponseEntity(userProfileNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Optional<Profile>> getProfileByUserId(@PathVariable Integer userId) throws ProfileNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileByUserId(userId));
        } catch(ProfileNotFoundException profileNotFoundException){
            return new ResponseEntity(profileNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createProfile(@RequestBody Profile newProfile) throws ProfileNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.createUserProfile(newProfile));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<String> updateProfile(@PathVariable Integer userId, @RequestBody Profile newProfile) throws ProfileNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.updateProfile(userId, newProfile));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Integer userId) throws ProfileNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.deleteProfile(userId));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}