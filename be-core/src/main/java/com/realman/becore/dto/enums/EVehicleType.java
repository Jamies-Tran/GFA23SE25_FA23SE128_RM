package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EVehicleType {
    CAR(0),
    BIKE(1),
    TAXI(2);

    @NonNull
    private final Integer value;
}
