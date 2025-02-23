package com.byte_sized.byte_sized.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;
    public UserNotFoundException () {}

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}