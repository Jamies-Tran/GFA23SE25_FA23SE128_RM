package com.realman.becore.controller.api.schedule.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realman.becore.dto.enums.EScheduleStatus;
import com.realman.becore.dto.enums.EShift;
import com.realman.becore.util.TimeZoneConfig;
import java.time.ZonedDateTime;

public record ScheduleResponse(
        Long scheduleId,
        Long staffId,
        EShift shift,
        @JsonFormat(pattern = "MM/dd/yyyy") LocalDate workingDate,
        LocalTime startShift,
        LocalTime endShift,
        EScheduleStatus scheduleStatus

) {
    public LocalDate workingDate() {
        return ZonedDateTime.of(workingDate.atStartOfDay(), TimeZoneConfig.zoneId()).toLocalDate();
    }

    public LocalTime startShift() {
        return ZonedDateTime.of(startShift.atDate(LocalDate.now()), TimeZoneConfig.zoneId())
                .toLocalTime();
    }

    public LocalTime endShift() {
        return ZonedDateTime.of(endShift.atDate(LocalDate.now()), TimeZoneConfig.zoneId())
                .toLocalTime();
    }
}
