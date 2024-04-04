package com.realman.becore.controller.api.booking.models;

import java.time.LocalDate;
import java.util.List;

import com.realman.becore.controller.api.booking.service.models.BookingServiceRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookingRequest(
                @NotNull LocalDate appointmentDate,
                @NotNull Long branchId,
                @NotNull List<BookingServiceRequest> bookingServices) {
}
