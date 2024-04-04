package com.realman.becore.service.booking;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.realman.becore.controller.api.booking.models.BookingId;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.BookingInfo;
import com.realman.becore.dto.booking.BookingMapper;
import com.realman.becore.dto.booking.BookingSearchCriteria;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.staff.booking.BookingStaff;
import com.realman.becore.dto.staff.booking.BookingStaffMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.booking.BookingRepository;
import com.realman.becore.service.booking.service.BookingServiceUseCaseService;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingQueryService {
        @NonNull
        private final BookingRepository bookingRepository;
        @NonNull
        private final BookingServiceUseCaseService bookingServiceUseCaseService;
        @NonNull
        private final BookingMapper bookingMapper;
        @NonNull
        private final BookingStaffMapper bookingStaffMapper;

        public Booking findById(BookingId bookingId) {
                List<BookingService> bookingServices = bookingServiceUseCaseService.findByBookingId(bookingId.value());
                BookingInfo bookingInfo = bookingRepository.findInfoById(bookingId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                Booking booking = bookingMapper.toDto(bookingInfo, bookingServices);
                return booking;
        }

        public Page<Booking> findAll(BookingSearchCriteria searchCriteria, Long accountId,
                        PageRequestCustom pageRequestCustom) {
                Map<Long, List<BookingService>> bookingServiceMap = bookingServiceUseCaseService
                                .findAll()
                                .stream().collect(Collectors.groupingBy(BookingService::bookingId));
                Page<Booking> bookings = bookingRepository.findAllInfo(searchCriteria, pageRequestCustom.pageRequest())
                                .map(booking -> {
                                        Long totalBookingPrice = bookingRepository
                                                        .findBookingPrice(booking.getBookingId()).stream()
                                                        .reduce(Long::sum)
                                                        .orElse(0L);
                                        return bookingMapper.toDto(booking,
                                                        bookingServiceMap.get(booking.getBookingId()),
                                                        totalBookingPrice);
                                });
                return bookings;
        }

        public List<BookingStaff> findByStaffId(Long staffId) {
                List<BookingInfo> bookingInfos = bookingRepository.findInfoByStaffId(staffId);
                return bookingInfos.stream().map(bookingStaffMapper::toDto).toList();
        }

        public Booking findByBookingServiceId(Long bookingServiceId) {
                BookingInfo foundBooking = bookingRepository.findByBookingServiceId(bookingServiceId)
                                .orElseThrow(() -> new ResourceNotFoundException());
                List<Boolean> allowProcess = bookingRepository
                                .hasBookingContainBookingServiceStatus(bookingServiceId, foundBooking.getBookingId());
                List<BookingService> bookingServices = bookingServiceUseCaseService
                                .findByBookingId(foundBooking.getBookingId());
                return bookingMapper.toDto(foundBooking, bookingServices,
                                allowProcess.stream().filter(t -> t == false).findAny().orElse(true));
        }
}
