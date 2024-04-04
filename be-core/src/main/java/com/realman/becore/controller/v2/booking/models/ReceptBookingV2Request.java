package com.realman.becore.controller.v2.booking.models;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record ReceptBookingV2Request(
                String phone,
                String firstName,
                String lastName,
                LocalDateTime appointmentDateV2,
                Long branchId,
                List<BookingServiceV2Request> bookingServices) {

}
