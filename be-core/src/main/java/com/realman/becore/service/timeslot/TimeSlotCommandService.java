package com.realman.becore.service.timeslot;

import java.time.LocalTime;
import java.util.List;

import org.mapstruct.ap.internal.util.Collections;
import org.springframework.stereotype.Service;

import com.realman.becore.repository.database.timeslot.TimeSlotEntity;
import com.realman.becore.repository.database.timeslot.TimeSlotRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSlotCommandService {
    @NonNull
    private final TimeSlotRepository timeSlotRepository;

    public void saveDefault() {
        if (timeSlotRepository.findAll().isEmpty()) {
            List<TimeSlotEntity> timeSlotFirstShifts = List.of(
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("07:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("07:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("07:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("08:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("08:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("08:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("09:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("09:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("09:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("10:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("10:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("10:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("11:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("11:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("11:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("12:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("12:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("12:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("13:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("13:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("13:40")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("14:00")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("14:20")).build(),
                    TimeSlotEntity.builder().shiftId(1L).time(LocalTime.parse("14:40")).build());
            List<TimeSlotEntity> timeSlotLastShifts = List.of(
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("15:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("15:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("15:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("16:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("16:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("16:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("17:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("17:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("17:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("18:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("18:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("18:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("19:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("19:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("19:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("20:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("20:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("20:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("21:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("21:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("21:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("22:00")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("22:20")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("22:40")).build(),
                    TimeSlotEntity.builder().shiftId(2L).time(LocalTime.parse("23:00")).build());
            List<TimeSlotEntity> timeSlots = Collections.join(timeSlotFirstShifts, timeSlotLastShifts).stream()
                    .distinct().toList();
            timeSlotRepository.saveAll(timeSlots);
        }
    }
}
