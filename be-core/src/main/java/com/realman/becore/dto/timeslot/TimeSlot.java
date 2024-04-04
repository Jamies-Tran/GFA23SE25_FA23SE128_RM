package com.realman.becore.dto.timeslot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.Builder;

@Builder
public record TimeSlot(
        Long timeSlotId,
        Long shiftId,
        LocalTime time,
        String timeText,
        Boolean isAvailable) {
    public String timeText() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }
}
