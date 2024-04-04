package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EBookingServiceStatus {
    ONGOING(0),
    CONFIRM(1),
    REQUEST_CONFIRM(2),
    PROCESSING(3),
    FINISHED(4),
    PENDING(5),
    LOCKED(6),
    CANCLED(7),
    PAID(8);

    private final Integer value;
}
