package com.realman.becore.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EErrorMessage {
    JWT_NOT_VALID(0),
    ACCOUNT_NOT_FOUND(1),
    CUSOMTER_NOT_FOUND(2),
    STAFF_NOT_FOUND(3),
    MANAGER_NOT_FOUND(4),
    SHOPOWNER_NOT_FOUND(5),
    RECEPTIONIST_NOT_FOUND(6),
    ITIMACY_LEVEL_NOT_FOUNT(7),
    ITIMACY_NOT_FOUNT(8),
    USERNAME_DUPLICATED(9),
    PHONE_DUPLICATED(10),
    ACCOUNT_NOT_VALID(11),
    PROFESSIONAL_NOT_FOUND(4);

    private final Integer value;
}
