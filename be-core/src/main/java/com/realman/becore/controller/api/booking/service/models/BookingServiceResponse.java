package com.realman.becore.controller.api.booking.service.models;

import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;
import com.realman.becore.dto.enums.EProfessional;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

public record BookingServiceResponse(
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
        String durationText,
        EBookingServiceStatus bookingServiceStatus,
        EProfessional professional,
        EBookingServiceType bookingServiceType,
        String bookingServiceTypeText,
        List<BookingResultResponse> bookingResults,
        Boolean allowUpdate) {

    public String durationText() {
        StringBuilder builder = new StringBuilder();
        if (Objects.nonNull(duration)) {
            return builder.append(duration.toHours()).append(":")
                    .append(duration.toMinutes()).append(":")
                    .append(duration.toSeconds())
                    .toString();
        }
        return builder.append(0).toString();
    }

    public String bookingServiceTypeText() {
        return Objects.nonNull(bookingServiceType) ? bookingServiceType.getText() : null;
    }
}
