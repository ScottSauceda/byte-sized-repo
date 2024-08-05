package com.byte_sized.byte_sized.exception;

public class ReviewNotFoundException extends RuntimeException {
    public String message;

    public ReviewNotFoundException() {}

    public ReviewNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}