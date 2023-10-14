package com.realman.becore.error_handlers.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource_Not_Found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
