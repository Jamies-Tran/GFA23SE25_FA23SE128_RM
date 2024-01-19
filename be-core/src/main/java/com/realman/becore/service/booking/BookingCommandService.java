package com.realman.becore.service.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.BookingMapper;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingStatus;
import com.realman.becore.error_handlers.exceptions.ResourceInvalidException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.booking.BookingEntity;
import com.realman.becore.repository.database.booking.BookingRepository;
import com.realman.becore.service.account.AccountCommandService;
import com.realman.becore.service.booking.service.BookingServiceCommandService;
import com.realman.becore.service.booking.service.BookingServiceQueryService;
import com.realman.becore.util.RequestContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingCommandService {
    @NonNull
    private final BookingRepository bookingRepository;
    @NonNull
    private final BookingServiceCommandService bookingServiceCommandService;
    @NonNull
    private final BookingServiceQueryService bookingServiceQueryService;
    @NonNull
    private final BookingMapper bookingMapper;
    @NonNull
    private final RequestContext requestContext;
    @Lazy
    @Autowired
    private AccountCommandService accountCommandService;

    public void save(Booking booking) {
        BookingEntity bookingEntity = bookingMapper.toEntity(booking, generateBookingCode(), EBookingStatus.ONGOING,
                requestContext.getCustomerId());
        BookingEntity savedBooking = bookingRepository.save(bookingEntity);
        bookingServiceCommandService.saveAll(savedBooking.getBookingId(), booking.bookingServices());
    }

    public void receptSave(ReceptBookingRequest receptBookingRequest) {
        BookingEntity booking = bookingMapper.toEntity(receptBookingRequest, generateBookingCode(),
                EBookingStatus.ONGOING);
        BookingEntity savedBooking = bookingRepository.save(booking);
        bookingServiceCommandService.saveAll(savedBooking.getBookingId(), bookingMapper.toDtos(
                receptBookingRequest.bookingServices()));
        accountCommandService.saveFromReceptBooking(receptBookingRequest);
    }

    public void finishBooking(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(ResourceNotFoundException::new);
        List<BookingService> bookingServices = bookingServiceQueryService.findByBookingId(bookingId);
        if (bookingServices.stream().allMatch(bs -> bs.bookingServiceStatus().equals(EBookingServiceStatus.FINISHED))) {
            bookingEntity.setBookingStatus(EBookingStatus.FINISHED);
            bookingRepository.save(bookingEntity);
        }
    }

    public void endBooking(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(ResourceNotFoundException::new);
        if (!booking.getBookingStatus().equals(EBookingStatus.FINISHED)) {
            throw new ResourceInvalidException();
        }
        booking.setBookingStatus(EBookingStatus.PAID);
        bookingRepository.save(booking);
        bookingServiceCommandService.endBookingService(bookingId);
    }

    private String generateBookingCode() {
        StringBuilder bookingCode = new StringBuilder();
        bookingCode.append("BOOK")
                .append("__").append(LocalDate.now().getDayOfMonth()).append(LocalDate.now().getMonthValue())
                .append(LocalDate.now().getYear()).append("_").append(LocalTime.now().getHour())
                .append(LocalTime.now().getMinute())
                .append(LocalTime.now().getSecond());
        return bookingCode.toString();
    }
}
