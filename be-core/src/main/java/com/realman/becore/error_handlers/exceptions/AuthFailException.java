package com.realman.becore.error_handlers.exceptions;

import com.realman.becore.dto.enums.EErrorMessage;

public class AuthFailException extends RuntimeException {
    public AuthFailException() {
        super(EErrorMessage.AUTHENTICATION_FAIL.getMessage());
    }
}
