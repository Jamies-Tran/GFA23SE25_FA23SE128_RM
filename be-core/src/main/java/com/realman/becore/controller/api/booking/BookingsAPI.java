package com.realman.becore.controller.api.booking;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.booking.models.BookingRequest;
import com.realman.becore.controller.api.booking.models.BookingResponse;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Booking", description = "Booking API")
@PreAuthorize("hasAnyAuthority({'booking:add', 'booking:view'})")
@RequestMapping("/v1/booking")
public interface BookingsAPI {
        @PostMapping
        void save(@RequestBody @Valid BookingRequest booking);

        @GetMapping
        PageImplResponse<BookingResponse> findAll(
                        @RequestParam(required = false, value = "accountId") Long accountId,
                        @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
                        @RequestParam(required = false, value = "customerId", defaultValue = "") Long customerId,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "pageRequest", defaultValue = "20") Integer pageSize);

        @GetMapping("/service")
        ValueResponse<BookingResponse> findByBookingServiceId(
                        @RequestParam(value = "bookingServiceId") Long bookingServiceId);
}
