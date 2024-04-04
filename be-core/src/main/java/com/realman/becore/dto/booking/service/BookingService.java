package com.realman.becore.dto.booking.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

import com.realman.becore.dto.booking.result.BookingResult;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;
import com.realman.becore.dto.enums.EProfessional;
import java.util.List;
import lombok.Builder;

@Builder
public record BookingService(
        Long bookingServiceId,
        Long bookingId,
        Long serviceId,
        Long staffId,
        Long ratingId,
        String bookingCode,
        String serviceName,
        Long servicePrice,
        String staffName,
        String staffPhone,
        String customerName,
        String customerPhone,
        LocalDate appointmentDate,
        LocalTime startAppointment,
        LocalTime actualStartAppointment,
        LocalTime endAppointment,
        LocalTime actualEndAppointment,
        Duration duration,
        EBookingServiceStatus bookingServiceStatus,
        EProfessional professional,
        EBookingServiceType bookingServiceType,
        List<BookingResult> bookingResults,
        Boolean allowUpdate) {

}
