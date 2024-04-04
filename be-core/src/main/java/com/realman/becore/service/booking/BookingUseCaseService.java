package com.realman.becore.service.booking;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.realman.becore.controller.api.booking.models.BookingId;
import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.BookingSearchCriteria;
import com.realman.becore.dto.staff.booking.BookingStaff;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingUseCaseService {
    @NonNull
    private final BookingCommandService bookingCommandService;
    @NonNull
    private final BookingQueryService bookingQueryService;

    @Transactional
    public void save(Booking booking) {
        bookingCommandService.save(booking);
    }

    @Transactional
    public void receptSave(ReceptBookingRequest receptBookingRequest) {
        bookingCommandService.receptSave(receptBookingRequest);
    }

    @Transactional
    public void finishBooking(Long bookingId) {
        bookingCommandService.finishBooking(bookingId);
    }

    @Transactional
    public void endBooking(Long bookingId) {
        bookingCommandService.endBooking(bookingId);
    }

    @Transactional
    public void confirmBooking(Long bookingId) {
        bookingCommandService.confirmBooking(bookingId);
    }

    @Transactional
    public void startBooking(Long bookingId) {
        bookingCommandService.startBooking(bookingId);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        bookingCommandService.cancelBooking(bookingId);
    }

    public Booking findById(BookingId bookingId) {
        return bookingQueryService.findById(bookingId);
    }

    public Page<Booking> findAll(BookingSearchCriteria searchCriteria, Long accountId,
            PageRequestCustom pageRequestCustom) {
        return bookingQueryService.findAll(searchCriteria, accountId, pageRequestCustom);
    }

    public List<BookingStaff> findByStaffId(Long staffId) {
        return bookingQueryService.findByStaffId(staffId);
    }

    public Booking findByBookingServiceId(Long bookingServiceId) {
        return bookingQueryService.findByBookingServiceId(bookingServiceId);
    }
}
