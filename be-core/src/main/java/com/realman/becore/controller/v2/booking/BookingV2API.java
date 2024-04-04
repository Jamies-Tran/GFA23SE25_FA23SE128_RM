package com.realman.becore.controller.v2.booking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.realman.becore.controller.v2.booking.models.ReceptBookingV2Request;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Booking V2", description = "Booking Ver 2")
@RequestMapping("/v2/booking")
public interface BookingV2API {
    @PostMapping("/receptionist")
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
    void receptBooking(@RequestBody @Valid ReceptBookingV2Request receptBookingRequest);
}
