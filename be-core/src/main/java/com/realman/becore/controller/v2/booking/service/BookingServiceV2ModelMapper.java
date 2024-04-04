package com.realman.becore.controller.v2.booking.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.controller.v2.booking.models.BookingServiceV2Request;
import com.realman.becore.dto.booking.service.BookingService;
import com.realman.becore.dto.enums.EBookingServiceType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingServiceV2ModelMapper {

    @Mapping(expression = "java(setBookingServiceType(dto))", target = "bookingServiceType")
    BookingServiceV2Request updateType(BookingServiceV2Request dto);

    @Mapping(expression = "java(startAppointment(model.startAppointmentV2()))", target = "startAppointment")
    @Mapping(expression = "java(endAppointment(model.endAppointmentV2()))", target = "endAppointment")
    BookingService toDto(BookingServiceV2Request model);

    default LocalTime startAppointment(LocalDateTime startAppointmentV2) {
        return startAppointmentV2.plusHours(7).toLocalTime();
    }

    default LocalTime endAppointment(LocalDateTime endAppointmentV2) {
        return endAppointmentV2.plusHours(7).toLocalTime();
    }

    default EBookingServiceType setBookingServiceType(BookingServiceV2Request bookingService) {
        if (Objects.isNull(bookingService.staffId())) {
            return EBookingServiceType.PICKUP_STYLIST;
        }
        return EBookingServiceType.CHOSEN_STYLIST;
    }
}
