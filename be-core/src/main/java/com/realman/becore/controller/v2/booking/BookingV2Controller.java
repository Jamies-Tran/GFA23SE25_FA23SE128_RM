package com.realman.becore.controller.v2.booking;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.controller.v2.booking.models.BookingV2ModelMapper;
import com.realman.becore.controller.v2.booking.models.ReceptBookingV2Request;
import com.realman.becore.controller.v2.booking.service.BookingServiceV2ModelMapper;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.service.booking.BookingUseCaseService;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingV2Controller implements BookingV2API {
    @NonNull
    private final BookingUseCaseService bookingUseCaseService;
    @NonNull
    private final BookingServiceV2ModelMapper bookingServiceV2ModelMapper;
    @NonNull
    private final BookingV2ModelMapper bookingV2ModelMapper;

    @Override
    public void receptBooking(@Valid ReceptBookingV2Request receptBookingRequest) {
        List<BookingService> bookingServices = receptBookingRequest.bookingServices()
                .stream().map(bs -> bookingServiceV2ModelMapper.updateType(bs))
                .map(bookingServiceV2ModelMapper::toDto)
                .toList();
        ReceptBookingRequest updateReceptBooking = bookingV2ModelMapper.updateBookingServices(
                receptBookingRequest,
                bookingServices);
        bookingUseCaseService.receptSave(updateReceptBooking);
    }

}
