package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ECategoryType {
    MASSAGE("Mát xa", 1),
    HAIRCUT("Cắt tóc", 2),
    OTHER("Khác", 3);

    private final String name;
    private final Integer priority;
}
