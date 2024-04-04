package com.realman.becore.service.booking.result;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.realman.becore.dto.booking.result.BookingResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingResultUseCaseService {
    @NonNull
    private final BookingResultCommandService bookingResultCommandService;
    @NonNull
    private final BookingResultQueryService bookingResultQueryService;

    @Transactional
    public void saveAll(List<BookingResult> bookingResults) {
        bookingResultCommandService.saveAll(bookingResults);
    }

    public List<BookingResult> findByBookingServiceId(Long bookingId) {
        return bookingResultQueryService.findByBookingServiceId(bookingId);
    }

    public Map<Long, List<BookingResult>> findAll() {
        return bookingResultQueryService.findAll();
    }
}
