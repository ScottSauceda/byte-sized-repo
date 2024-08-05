package com.byte_sized.byte_sized.exception;

public class ProfileNotFoundException extends RuntimeException {
    private String message;

    public ProfileNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}