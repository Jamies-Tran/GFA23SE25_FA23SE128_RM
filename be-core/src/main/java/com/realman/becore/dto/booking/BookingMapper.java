package com.realman.becore.dto.booking;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.controller.api.booking.service.models.BookingServiceRequest;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.enums.EBookingStatus;
import com.realman.becore.repository.database.booking.BookingEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
    @Mapping(source = "bookingStatus", target = "bookingStatus")
    @Mapping(source = "bookingCode", target = "bookingCode")
    @Mapping(source = "customerId", target = "customerId")
    BookingEntity toEntity(Booking dto, String bookingCode, EBookingStatus bookingStatus, Long customerId);

    BookingEntity toEntity(BookingInfo info);

    Booking toDto(BookingInfo info, List<BookingService> bookingServices);

    Booking toDto(BookingInfo info, List<BookingService> bookingServices, Boolean allowProcess);

    Booking toDto(BookingEntity entity, List<BookingService> bookingServices);

    @Mapping(source = "totalBookingPrice", target = "totalBookingPrice", defaultValue = "0L")
    Booking toDto(BookingInfo info, List<BookingService> bookingServices, Long totalBookingPrice);

    BookingEntity toEntity(ReceptBookingRequest dto, Long customerId, String bookingCode, EBookingStatus bookingStatus);

    List<BookingService> toDtos(List<BookingServiceRequest> bookingServices);
}
