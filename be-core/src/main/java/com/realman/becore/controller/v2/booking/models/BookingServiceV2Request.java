package com.realman.becore.controller.v2.booking.models;

import java.time.LocalDateTime;
import com.realman.becore.dto.enums.EBookingServiceType;
import lombok.Builder;

@Builder
public record BookingServiceV2Request(
                Long serviceId,
                Long staffId,
                EBookingServiceType bookingServiceType,
                LocalDateTime startAppointmentV2,
                LocalDateTime endAppointmentV2) {

}
