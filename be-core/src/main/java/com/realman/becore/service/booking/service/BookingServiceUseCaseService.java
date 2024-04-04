package com.realman.becore.service.booking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.controller.api.booking.service.models.AccountId;
import com.realman.becore.controller.api.booking.service.models.BookingResultRequest;
import com.realman.becore.controller.api.booking.service.models.BookingServiceId;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.booking.service.BookingServiceSearchCriteria;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceUseCaseService {
    @NonNull
    private final BookingServiceQueryService bookingServiceQueryService;
    @NonNull
    private final BookingServiceCommandService bookingServiceCommandService;

    @Transactional
    public BookingService startService(BookingServiceId bookingServiceId, AccountId accountId) {
        return bookingServiceCommandService.startService(bookingServiceId);
    }

    @Transactional
    public void cancelBookingService(Long bookingServiceId) {
        bookingServiceCommandService.cancelBookingService(bookingServiceId);
    }

    @Transactional
    public BookingService endService(BookingServiceId bookingServiceId, BookingResultRequest bookingResultRequest,
            AccountId accountId) {
        return bookingServiceCommandService.finishService(bookingServiceId, bookingResultRequest);
    }

    @Transactional
    public void chooseStylist(Long bookingServiceId, Long staffId) {
        bookingServiceCommandService.chooseStylist(staffId, bookingServiceId);
    }

    @Transactional
    public void chooseStylistForAll(Long staffId, Long bookingId) {
        bookingServiceCommandService.chooseStylistForAll(bookingId, staffId);
    }

    public BookingService findById(Long bookingServiceId) {
        return bookingServiceQueryService.findById(bookingServiceId);
    }

    public BookingService confirmService(BookingServiceId bookingServiceId, AccountId accountId) {
        return bookingServiceCommandService.confirmService(bookingServiceId);
    }

    public List<BookingService> findByBookingId(Long bookingId) {
        return bookingServiceQueryService.findByBookingId(bookingId);
    }

    public List<BookingService> findAll() {
        return bookingServiceQueryService.findAll();
    }

    public Page<BookingService> findByStaffId(Long staffId, PageRequestCustom pageRequestCustom) {
        return bookingServiceQueryService.findByStaffId(pageRequestCustom);
    }

    public Page<BookingService> findAll(BookingServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return bookingServiceQueryService.findAll(searchCriteria, pageRequestCustom);
    }
}
