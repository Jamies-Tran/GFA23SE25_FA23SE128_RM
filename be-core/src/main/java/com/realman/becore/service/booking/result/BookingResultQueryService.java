package com.realman.becore.service.booking.result;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.realman.becore.dto.booking.result.BookingResult;
import com.realman.becore.dto.booking.result.BookingResultMapper;
import com.realman.becore.repository.database.booking.result.BookingResultRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingResultQueryService {
    @NonNull
    private final BookingResultRepository bookingResultRepository;
    @NonNull
    private final BookingResultMapper bookingResultMapper;

    public List<BookingResult> findByBookingServiceId(Long bookingServiceId) {
        List<BookingResult> bookingResults = bookingResultRepository
                .findByBookingId(bookingServiceId).stream()
                .map(bookingResultMapper::toDto).toList();
        return bookingResults;
    }

    public Map<Long, List<BookingResult>> findAll() {
        Map<Long, List<BookingResult>> bookingResults = bookingResultRepository.findAll()
                .stream().map(bookingResultMapper::toDto)
                .collect(Collectors.groupingBy(BookingResult::bookingServiceId));
        return bookingResults;
    }
}
