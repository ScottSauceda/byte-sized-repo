package com.byte_sized.byte_sized.exception;

public class LocationNotFoundException extends RuntimeException {
    private String message;
    public LocationNotFoundException() {}

    public LocationNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}