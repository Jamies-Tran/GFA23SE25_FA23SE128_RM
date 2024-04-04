package com.realman.becore.controller.api.timeslot.models;

import java.time.LocalTime;
import lombok.Builder;

@Builder
public record TimeSlotResponse(
                Long timeSlotId,
                Long shiftId,
                LocalTime time,
                String timeText,
                Boolean isAvailable) {
}
