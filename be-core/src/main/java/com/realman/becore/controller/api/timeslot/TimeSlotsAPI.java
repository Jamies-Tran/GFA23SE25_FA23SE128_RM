package com.realman.becore.controller.api.timeslot;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.timeslot.models.TimeSlotResponse;
import com.realman.becore.util.response.ListResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Time Slot", description = "Time slot management")
@RequestMapping("/v1/time-slot")
public interface TimeSlotsAPI {
    @GetMapping
    ListResponse<TimeSlotResponse> findTimeSlots(
            @RequestParam(required = false, value = "chosenDate", defaultValue = "") @DateTimeFormat(iso = ISO.DATE) LocalDate chosenDate,
            @RequestParam(value = "staffId", defaultValue = "1") Long staffId);
}
