package com.realman.becore.error_handlers.exceptions;

import com.realman.becore.dto.enums.EErrorMessage;

public class ResourceDuplicateException extends RuntimeException {
    public ResourceDuplicateException() {
        super(EErrorMessage.RESOURCE_DUPLICATED.getMessage());
    }
}
