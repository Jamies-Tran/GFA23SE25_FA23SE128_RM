package com.realman.becore.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EErrorDes {
    AUTH_FAIL(0),
    RESOURCE_NOT_FOUND(1),
    RESOURCE_DUPLICATED(2),
    RESOURCE_NOT_VALID(3);

    private final Integer value;
}
