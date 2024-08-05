package com.byte_sized.byte_sized.exception;

public class RestaurantNotFoundException extends RuntimeException {
    private String message;
    public RestaurantNotFoundException() {}

    public RestaurantNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}