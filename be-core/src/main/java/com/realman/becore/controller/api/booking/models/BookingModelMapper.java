package com.realman.becore.controller.api.booking.models;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.dto.booking.Booking;
import com.realman.becore.dto.booking.service.BookingService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingModelMapper {
    Booking toDto(BookingRequest model);

    BookingResponse toModel(Booking dto);

    @Mapping(source = "bookingServices", target = "bookingServices")
    ReceptBookingRequest updateBookingServices(ReceptBookingRequest dto, List<BookingService> bookingServices);
}