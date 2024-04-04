package com.realman.becore.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EBookingStatus {
    ONGOING(0),
    CONFIRM(1),
    PROCESSING(2),
    FINISHED(3),
    PENDING(4),
    CANCLED(5),
    PAID(6);

    private Integer value;
}
