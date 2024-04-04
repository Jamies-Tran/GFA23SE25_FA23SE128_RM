package com.realman.becore.dto.shift;

import java.time.LocalTime;

import com.realman.becore.dto.enums.EShift;

import lombok.Builder;

@Builder
public record Shift(
        EShift shift,
        LocalTime startShift,
        LocalTime endShift) {

}
