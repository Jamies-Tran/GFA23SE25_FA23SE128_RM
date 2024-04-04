package com.realman.becore.controller.api.booking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.realman.becore.controller.api.booking.models.BookingResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Booking", description = "Booking API")
@RequestMapping("/v1/booking/{bookingId}")
public interface BookingAPI {
    @GetMapping
    ValueResponse<BookingResponse> findById(@PathVariable Long bookingId);

    @PutMapping("/end")
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
    void endBooking(@PathVariable Long bookingId);

    @PutMapping("/cancel")
    @PreAuthorize("hasAnyRole({'ROLE_CUSTOMER', 'ROLE_RECEPTIONIST'})")
    void cancelBooking(@PathVariable Long bookingId);
}
