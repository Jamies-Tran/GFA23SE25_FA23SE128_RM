package com.realman.becore.controller.api.booking.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.realman.becore.controller.api.booking.service.models.BookingResultRequest;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Booking Service", description = "Booking service API")
@RequestMapping("/v1/booking-service/{bookingServiceId}")
public interface BookingServiceAPI {
        @PutMapping("/start-service")
        void startService(@PathVariable Long bookingServiceId, @RequestParam(required = false) Long accountId);

        @PutMapping("/finish-service")
        void endService(
                        @PathVariable Long bookingServiceId,
                        @RequestBody BookingResultRequest bookingResultRequest,
                        @RequestParam(required = false) Long accountId);

        @PutMapping("/confirm-service")
        void confirmService(
                        @PathVariable Long bookingServiceId,
                        @RequestParam(required = false) Long accountId);

        @PutMapping("/cancel-service")
        @PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
        void cancelBookingService(@PathVariable Long bookingServiceId);

        @PutMapping("/choose-stylist")
        @PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
        void chooseStylist(@RequestParam Long staffId, @PathVariable Long bookingServiceId);
}
