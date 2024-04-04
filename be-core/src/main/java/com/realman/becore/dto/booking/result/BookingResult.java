package com.realman.becore.dto.booking.result;

import lombok.Builder;

@Builder
public record BookingResult(
        Long bookingResultId,
        Long bookingServiceId,
        String bookingResultImg) {

}
