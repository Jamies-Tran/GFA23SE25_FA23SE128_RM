package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EBookingServiceType {
    CHOSEN_STYLIST("Đã chọn stylist"),
    PICKUP_STYLIST("Chọn hộ");

    private final String text;
}
