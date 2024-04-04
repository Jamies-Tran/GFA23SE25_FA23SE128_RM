package com.realman.becore.controller.api.booking.service.models;

import lombok.Builder;

@Builder
public record BookingResultResponse(
        Long bookingResultId,
        Long bookingServiceId,
        String bookingResultImg) {

}
