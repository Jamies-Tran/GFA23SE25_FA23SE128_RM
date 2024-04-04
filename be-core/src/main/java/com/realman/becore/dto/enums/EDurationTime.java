package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EDurationTime {
    MINUTE("Phút"),
    HOUR("Giờ");

    private final String text;
}
