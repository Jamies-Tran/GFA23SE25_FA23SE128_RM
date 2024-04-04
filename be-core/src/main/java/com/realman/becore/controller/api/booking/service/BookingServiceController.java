package com.realman.becore.controller.api.booking.service;

import org.springframework.web.bind.annotation.RestController;
import com.realman.becore.controller.api.booking.service.models.AccountId;
import com.realman.becore.controller.api.booking.service.models.BookingResultRequest;
import com.realman.becore.controller.api.booking.service.models.BookingServiceId;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.service.booking.BookingUseCaseService;
import com.realman.becore.service.booking.service.BookingServiceUseCaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingServiceController implements BookingServiceAPI {
    @NonNull
    private final BookingServiceUseCaseService bookingServiceUseCaseService;
    @NonNull
    private final BookingUseCaseService bookingUseCaseService;

    @Override
    public void startService(Long bookingServiceId, Long accountId) {
        BookingService bookingService = bookingServiceUseCaseService
                .startService(new BookingServiceId(bookingServiceId), new AccountId(accountId));
        bookingUseCaseService.startBooking(bookingService.bookingId());
    }

    @Override
    public void endService(Long bookingServiceId, BookingResultRequest bookingResultRequest, Long accountId) {
        BookingService bookingService = bookingServiceUseCaseService.endService(new BookingServiceId(bookingServiceId),
                bookingResultRequest,
                new AccountId(accountId));
        bookingUseCaseService.finishBooking(bookingService.bookingId());
    }

    @Override
    public void confirmService(Long bookingServiceId, Long accountId) {
        BookingService bookingService = bookingServiceUseCaseService.confirmService(
                new BookingServiceId(bookingServiceId),
                new AccountId(accountId));
        bookingUseCaseService.confirmBooking(bookingService.bookingId());
    }

    @Override
    public void cancelBookingService(Long bookingServiceId) {
        bookingServiceUseCaseService.cancelBookingService(bookingServiceId);
    }

    @Override
    public void chooseStylist(Long staffId, Long bookingServiceId) {
        bookingServiceUseCaseService.chooseStylist(bookingServiceId, staffId);
    }

}
