package com.realman.becore.error_handlers.exceptions;

import com.realman.becore.dto.enums.EErrorMessage;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(EErrorMessage.RESOURCE_NOT_FOUND.getMessage());
    }
}
