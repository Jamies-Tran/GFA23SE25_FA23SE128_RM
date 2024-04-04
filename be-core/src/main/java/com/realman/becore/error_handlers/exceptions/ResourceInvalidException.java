package com.realman.becore.error_handlers.exceptions;

import com.realman.becore.dto.enums.EErrorMessage;

public class ResourceInvalidException extends RuntimeException {
    public ResourceInvalidException() {
        super(EErrorMessage.RESOURCE_INVALID.getMessage());
    }

    public ResourceInvalidException(String msg) {
        super(msg);
    }
}
