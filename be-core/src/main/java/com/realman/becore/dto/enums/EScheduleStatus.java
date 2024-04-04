package com.realman.becore.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EScheduleStatus {
    ONGOING(0),
    PRESENT(1),
    ABSENT(2);

    private Integer value;
}
