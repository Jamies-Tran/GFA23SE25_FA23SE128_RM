package com.realman.becore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EBookingStatus {
    ONGOING(0),
    COMFIRMED(1),
    CANCLED(2),
    REJECTED(3);

    private Integer value;
}
