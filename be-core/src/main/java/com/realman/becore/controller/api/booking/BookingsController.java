package com.realman.becore.controller.api.booking;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.booking.models.BookingModelMapper;
import com.realman.becore.controller.api.booking.models.BookingRequest;
import com.realman.becore.controller.api.booking.models.BookingResponse;
import com.realman.becore.controller.api.booking.service.models.BookingServiceModelMapper;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.BookingSearchCriteria;
import com.realman.becore.service.booking.BookingUseCaseService;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;
import com.realman.becore.util.response.ValueResponse;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingsController implements BookingsAPI {
        @NonNull
        private final BookingUseCaseService bookingUseCaseService;
        @NonNull
        private final BookingModelMapper bookingModelMapper;
        @NonNull
        private final BookingServiceModelMapper bookingServiceModelMapper;

        @Override
        public void save(@Valid BookingRequest booking) {
                Booking bookingDto = bookingModelMapper.toDto(booking);
                bookingUseCaseService.save(bookingDto);
        }

        @Override
        public PageImplResponse<BookingResponse> findAll(Long accountId, Long branchId, Long customerId,
                        Integer current,
                        Integer pageSize) {
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current);
                BookingSearchCriteria searchCriteria = BookingSearchCriteria.builder().branchId(branchId)
                                .customerId(customerId).build();
                Page<BookingResponse> bookingResponses = bookingUseCaseService
                                .findAll(searchCriteria, accountId, pageRequestCustom)
                                .map(bookingModelMapper::toModel);
                return new PageImplResponse<>(bookingResponses.getContent(), bookingResponses.getTotalElements(),
                                bookingResponses.getTotalPages(), bookingResponses.getSize(),
                                pageRequestCustom.current());
        }

        @Override
        public ValueResponse<BookingResponse> findByBookingServiceId(Long bookingServiceId) {
                Booking booking = bookingUseCaseService.findByBookingServiceId(bookingServiceId);
                return new ValueResponse<BookingResponse>(bookingModelMapper.toModel(booking));
        }

}
