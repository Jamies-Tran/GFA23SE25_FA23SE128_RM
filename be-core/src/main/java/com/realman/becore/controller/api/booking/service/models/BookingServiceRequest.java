package com.realman.becore.controller.api.booking.service.models;

import java.time.LocalTime;

import com.realman.becore.dto.enums.EBookingServiceType;

public record BookingServiceRequest(
        Long serviceId,
        Long staffId,
        EBookingServiceType bookingServiceType,
        LocalTime startAppointment,
        LocalTime endAppointment) {
}
