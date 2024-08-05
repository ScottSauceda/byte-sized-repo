package com.byte_sized.byte_sized.exception;

public class RestaurantImageNotFoundException extends RuntimeException {

    private String message;

    public RestaurantImageNotFoundException() {}

    public RestaurantImageNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
