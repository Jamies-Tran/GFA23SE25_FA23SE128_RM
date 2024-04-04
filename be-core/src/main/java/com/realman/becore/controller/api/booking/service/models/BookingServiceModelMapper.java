package com.realman.becore.controller.api.booking.service.models;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.enums.EBookingServiceType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingServiceModelMapper {
    BookingServiceResponse toModel(BookingService dto);

    BookingService toDto(BookingServiceRequest model);

    @Mapping(expression = "java(setBookingServiceType(dto))", target = "bookingServiceType")
    BookingServiceRequest updateType(BookingServiceRequest dto);

    default EBookingServiceType setBookingServiceType(BookingServiceRequest bookingService) {
        if (Objects.isNull(bookingService.staffId())) {
            return EBookingServiceType.PICKUP_STYLIST;
        }
        return EBookingServiceType.CHOSEN_STYLIST;
    }
}
