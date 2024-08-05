package com.byte_sized.byte_sized.exception;

public class ImageTypeNotFoundException extends RuntimeException {

    private String message;
    public ImageTypeNotFoundException() {}

    public ImageTypeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
