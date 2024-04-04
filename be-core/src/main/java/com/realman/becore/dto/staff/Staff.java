package com.realman.becore.dto.staff;

import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.dto.staff.booking.BookingStaff;

import java.util.List;
import lombok.Builder;

@Builder
public record Staff(
        Long staffId,
        Long accountId,
        Double averageRating,
        List<BookingStaff> bookingList,
        List<Schedule> scheduleList,
        EProfessional professional) {
}
