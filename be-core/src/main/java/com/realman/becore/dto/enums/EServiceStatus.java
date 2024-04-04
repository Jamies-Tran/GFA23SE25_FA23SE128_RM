package com.realman.becore.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EServiceStatus {
    ACTIVATING(0),
    STOP_OPERATING(1);

    private Integer value;
}
