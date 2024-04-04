package com.realman.becore.service.booking.result;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.booking.result.BookingResult;
import com.realman.becore.dto.booking.result.BookingResultMapper;
import com.realman.becore.repository.database.booking.result.BookingResultEntity;
import com.realman.becore.repository.database.booking.result.BookingResultRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingResultCommandService {
    @NonNull
    private final BookingResultRepository bookingResultRepository;
    @NonNull
    private final BookingResultMapper bookingResultMapper;

    public void saveAll(List<BookingResult> bookingResults) {
        List<BookingResultEntity> saveBookingResults = bookingResults.stream()
                .map(b -> bookingResultMapper.toEntity(b)).toList();
        bookingResultRepository.saveAll(saveBookingResults);
    }
}
