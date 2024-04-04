package com.realman.becore.controller.api.booking.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.booking.service.models.BookingServiceResponse;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;
import com.realman.becore.util.response.PageImplResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;

@Tag(name = "Booking Service", description = "Booking service management")
@RequestMapping("/v1/booking-service")
@PreAuthorize("hasAnyRole({'ROLE_STAFF','ROLE_RECEPTIONIST'})")
public interface BookingServicesAPI {
        @GetMapping
        PageImplResponse<BookingServiceResponse> findByStaffId(
                        @RequestParam(required = false, value = "staffId") Long staffId,
                        @RequestParam(required = false, value = "sorter", defaultValue = "bookingServiceId") String sorter,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);

        @GetMapping("/all")
        PageImplResponse<BookingServiceResponse> findAll(
                        @RequestParam(required = false, value = "search", defaultValue = "") String search,
                        @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
                        @RequestParam(required = false, value = "bookingServiceStatus", defaultValue = "") EBookingServiceStatus bookingServiceStatus,
                        @RequestParam(required = false, value = "bookingServiceType", defaultValue = "") EBookingServiceType bookingServiceType,
                        @RequestParam(required = false, value = "sorter", defaultValue = "bookingServiceId") String sorter,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);

        @PutMapping("/choose-stylist/all")
        void chooseStylistForAll(@RequestParam Long bookingId, @RequestParam Long staffId);

}
