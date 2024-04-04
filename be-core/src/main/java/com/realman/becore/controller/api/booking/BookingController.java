package com.realman.becore.controller.api.booking;

import org.springframework.web.bind.annotation.RestController;
import com.realman.becore.controller.api.booking.models.BookingId;
import com.realman.becore.controller.api.booking.models.BookingModelMapper;
import com.realman.becore.controller.api.booking.models.BookingResponse;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.service.booking.BookingUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingController implements BookingAPI {
    @NonNull
    private final BookingUseCaseService bookingUseCaseService;
    @NonNull
    private final BookingModelMapper bookingModelMapper;

    @Override
    public ValueResponse<BookingResponse> findById(Long bookingId) {
        Booking booking = bookingUseCaseService.findById(new BookingId(bookingId));
        return new ValueResponse<BookingResponse>(bookingModelMapper.toModel(booking));
    }

    @Override
    public void endBooking(Long bookingId) {
        bookingUseCaseService.endBooking(bookingId);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingUseCaseService.cancelBooking(bookingId);
    }

}
