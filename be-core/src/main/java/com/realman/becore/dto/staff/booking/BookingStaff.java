package com.realman.becore.dto.staff.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import lombok.Builder;

@Builder
public record BookingStaff(
                Long bookingId,
                Long bookingServiceId,
                LocalDate appointmentDate,
                LocalTime startAppointment,
                LocalTime endAppointment,
                EBookingServiceStatus bookingServiceStatus) {
}
