package com.realman.becore.dto.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

import com.realman.becore.dto.enums.EScheduleStatus;
import com.realman.becore.dto.enums.EShift;

import lombok.Builder;

@Builder
public record Schedule(
        Long scheduleId,
        Long staffId,
        Long shiftId,
        EShift shift,
        LocalDate workingDate,
        LocalTime startShift,
        LocalTime endShift,
        EScheduleStatus scheduleStatus) {

}
