package com.realman.becore.dto.booking;

import java.time.LocalDate;
import java.util.List;

import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.enums.EBookingStatus;

import lombok.Builder;

@Builder
public record Booking(
        Long bookingId,
        Long customerId,
        Long branchId,
        String bookingCode,
        String bookingOwnerName,
        String bookingOwnerPhone,
        String branchAddress,
        String branchName,
        LocalDate appointmentDate,
        List<BookingService> bookingServices,
        Long totalBookingPrice,
        EBookingStatus bookingStatus,
        Boolean allowProcess) {
}
