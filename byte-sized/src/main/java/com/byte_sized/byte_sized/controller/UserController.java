package com.byte_sized.byte_sized.controller;

import com.byte_sized.byte_sized.data.NewUserInformation;
import com.byte_sized.byte_sized.data.UserInformation;
import com.byte_sized.byte_sized.exception.ProfileNotFoundException;
import com.byte_sized.byte_sized.exception.UserNotFoundException;
import com.byte_sized.byte_sized.model.Profile;
import com.byte_sized.byte_sized.model.User;
import com.byte_sized.byte_sized.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> getUsers() throws UserNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserInformation>> getAllUserInformation() throws UserNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserInformation());
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserInformation> getUserByUserId(@PathVariable Integer userId) throws UserNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUserId(userId));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@RequestBody NewUserInformation newUserInformation) throws UserNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(newUserInformation));
        } catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}