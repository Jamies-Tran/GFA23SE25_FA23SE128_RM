package com.realman.becore.controller.api.booking.service.models;

import lombok.Builder;
import java.util.List;

@Builder
public record BookingResultRequest(List<String> bookingResultImgs) {
}
