package com.realman.becore.controller.api.timeslot;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.timeslot.models.TimeSlotModelMapper;
import com.realman.becore.controller.api.timeslot.models.TimeSlotResponse;
import com.realman.becore.service.timeslot.TimeSlotUseCaseService;
import com.realman.becore.util.response.ListResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TimeSlotsController implements TimeSlotsAPI {
    @NonNull
    private final TimeSlotUseCaseService timeSlotUseCaseService;
    @NonNull
    private final TimeSlotModelMapper timeSlotModelMapper;

    @Override
    public ListResponse<TimeSlotResponse> findTimeSlots(LocalDate chosenDate, Long staffId) {
        List<TimeSlotResponse> timeSlots = timeSlotUseCaseService
                .findTimeSlots(chosenDate, staffId).stream().map(timeSlotModelMapper::toModel).toList();
        return new ListResponse<>(timeSlots);
    }

}
