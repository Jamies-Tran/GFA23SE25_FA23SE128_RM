package com.realman.becore.controller.v2.booking.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.dto.booking.service.BookingService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingV2ModelMapper {
    @Mapping(source = "bookingServices", target = "bookingServices")
    @Mapping(target = "appointmentDate", expression = "java(appointmentDate(dto.appointmentDateV2()))")
    ReceptBookingRequest updateBookingServices(ReceptBookingV2Request dto, List<BookingService> bookingServices);

    default LocalDate appointmentDate(LocalDateTime appointmentDateV2) {
        return appointmentDateV2.plusHours(7).toLocalDate();
    }
}
