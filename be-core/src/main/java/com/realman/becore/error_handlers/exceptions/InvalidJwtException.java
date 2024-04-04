package com.realman.becore.error_handlers.exceptions;

import com.realman.becore.dto.enums.EErrorMessage;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException() {
        super(EErrorMessage.JWT_NOT_VALID.getMessage());
    }
}
