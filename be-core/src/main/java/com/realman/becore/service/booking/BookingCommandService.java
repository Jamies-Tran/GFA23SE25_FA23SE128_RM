package com.realman.becore.service.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.BookingInfo;
import com.realman.becore.dto.booking.BookingMapper;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingStatus;
import com.realman.becore.error_handlers.exceptions.ResourceInvalidException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.booking.BookingEntity;
import com.realman.becore.repository.database.booking.BookingRepository;
import com.realman.becore.service.account.AccountCommandService;
import com.realman.becore.service.booking.service.BookingServiceCommandService;
import com.realman.becore.service.booking.service.BookingServiceQueryService;
import com.realman.becore.service.twilio.TwilioUseCaseService;
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
        @NonNull
        private final TwilioUseCaseService twilioUseCaseService;
        @Lazy
        @Autowired
        private AccountCommandService accountCommandService;

        public void save(Booking booking) {
                BookingEntity bookingEntity = bookingMapper.toEntity(booking, generateBookingCode(),
                                EBookingStatus.ONGOING,
                                requestContext.getCustomerId());
                BookingEntity savedBooking = bookingRepository.save(bookingEntity);
                List<BookingService> bookingServices = bookingServiceCommandService.saveAll(savedBooking.getBookingId(),
                                booking.bookingServices());
                BookingInfo saveBookingInfo = bookingRepository.findInfoById(savedBooking.getBookingId())
                                .orElseThrow(ResourceNotFoundException::new);
                twilioUseCaseService.informBooking(requestContext.getAccountPhone(),
                                bookingMapper.toDto(saveBookingInfo, bookingServices));
        }

        public void receptSave(ReceptBookingRequest receptBookingRequest) {
                Customer customer = accountCommandService.saveFromReceptBooking(receptBookingRequest);
                BookingEntity booking = bookingMapper.toEntity(receptBookingRequest, customer.customerId(),
                                generateBookingCode(),
                                EBookingStatus.ONGOING);
                BookingEntity savedBooking = bookingRepository.save(booking);
                List<BookingService> bookingServices = bookingServiceCommandService.saveAll(savedBooking.getBookingId(),
                                bookingMapper.toDtos(
                                                receptBookingRequest.bookingServices()));
                twilioUseCaseService.informBooking(receptBookingRequest.phone(),
                                bookingMapper.toDto(savedBooking, bookingServices));
        }

        public void finishBooking(Long bookingId) {
                BookingEntity bookingEntity = bookingRepository.findById(bookingId)
                                .orElseThrow(ResourceNotFoundException::new);
                List<BookingService> bookingServices = bookingServiceQueryService.findByBookingId(bookingId);
                if (bookingServices.stream()
                                .allMatch(bs -> bs.bookingServiceStatus().equals(EBookingServiceStatus.FINISHED))) {
                        bookingEntity.setBookingStatus(EBookingStatus.FINISHED);
                        bookingRepository.save(bookingEntity);
                }
        }

        public void confirmBooking(Long bookingId) {
                BookingEntity bookingEntity = bookingRepository.findById(bookingId)
                                .orElseThrow(ResourceNotFoundException::new);
                List<BookingService> bookingServices = bookingServiceQueryService.findByBookingId(bookingId);
                if (bookingServices.stream()
                                .allMatch(bs -> bs.bookingServiceStatus().equals(EBookingServiceStatus.CONFIRM))) {
                        bookingEntity.setBookingStatus(EBookingStatus.CONFIRM);
                        bookingRepository.save(bookingEntity);
                }
        }

        public void startBooking(Long bookingId) {
                BookingEntity bookingEntity = bookingRepository.findById(bookingId)
                                .orElseThrow(ResourceNotFoundException::new);
                List<BookingService> bookingServices = bookingServiceQueryService.findByBookingId(bookingId);
                if (bookingServices.stream()
                                .anyMatch(bs -> bs.bookingServiceStatus().equals(EBookingServiceStatus.PROCESSING))) {
                        bookingEntity.setBookingStatus(EBookingStatus.PROCESSING);
                        bookingRepository.save(bookingEntity);
                }
        }

        public void endBooking(Long bookingId) {
                BookingInfo bookingInfo = bookingRepository.findInfoById(bookingId)
                                .orElseThrow(ResourceNotFoundException::new);
                BookingEntity foundBooking = bookingMapper.toEntity(bookingInfo);
                List<BookingService> bookingServices = bookingServiceQueryService.findByBookingId(bookingId);
                if (!foundBooking.getBookingStatus().equals(EBookingStatus.FINISHED)) {
                        throw new ResourceInvalidException();
                }
                foundBooking.setBookingStatus(EBookingStatus.PAID);
                bookingRepository.save(foundBooking);
                bookingServiceCommandService.endBookingService(bookingId);

                twilioUseCaseService.informEndBooking(bookingInfo.getBookingOwnerPhone(),
                                bookingMapper.toDto(bookingInfo, bookingServices));
        }

        public void cancelBooking(Long bookingId) {
                BookingEntity booking = bookingRepository.findById(bookingId)
                                .orElseThrow(ResourceNotFoundException::new);
                if (!booking.getBookingStatus().equals(EBookingStatus.ONGOING)) {
                        throw new ResourceInvalidException();
                }
                booking.setBookingStatus(EBookingStatus.CANCLED);
                bookingServiceCommandService.cancelAllBookingService(bookingId);
        }

        private String generateBookingCode() {
                StringBuilder bookingCode = new StringBuilder();
                bookingCode.append("BOOK")
                                .append("__").append(LocalDate.now().getDayOfMonth())
                                .append(LocalDate.now().getMonthValue())
                                .append(LocalDate.now().getYear()).append("_").append(LocalTime.now().getHour())
                                .append(LocalTime.now().getMinute())
                                .append(LocalTime.now().getSecond());
                return bookingCode.toString();
        }
}
