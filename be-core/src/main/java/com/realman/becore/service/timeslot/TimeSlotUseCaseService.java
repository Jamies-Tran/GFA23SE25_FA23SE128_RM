package com.realman.becore.service.timeslot;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.timeslot.TimeSlot;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSlotUseCaseService {
    @NonNull
    private final TimeSlotCommandService timeSlotCommandService;
    @NonNull
    private final TimeSlotQueryService timeSlotQueryService;

    @Transactional
    public void saveDefault() {
        timeSlotCommandService.saveDefault();
    }

    public List<TimeSlot> findTimeSlots(LocalDate chosenDate, Long staffId) {
        return timeSlotQueryService.findTimeSlots(chosenDate, staffId);
    }
}
