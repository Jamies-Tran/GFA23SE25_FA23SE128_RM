package com.realman.becore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EItimacyLevel {
    UNRANK(0),
    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4);

    private Integer value;
}
