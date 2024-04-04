package com.realman.becore.controller.api.booking.models;

import java.time.LocalDate;
import java.util.List;
import com.realman.becore.controller.api.booking.service.models.BookingServiceRequest;

public record ReceptBookingRequest(
        String phone,
        String firstName,
        String lastName,
        LocalDate appointmentDate,
        Long branchId,
        List<BookingServiceRequest> bookingServices) {
}
