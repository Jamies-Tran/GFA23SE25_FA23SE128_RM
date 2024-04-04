package com.realman.becore.dto.booking.service;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.dto.booking.result.BookingResult;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;
import com.realman.becore.repository.database.booking.service.BookingServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingServiceMapper {
    @Mapping(source = "bookingId", target = "bookingId")
    @Mapping(source = "bookingServiceStatus", target = "bookingServiceStatus")
    @Mapping(source = "bookingServiceType", target = "bookingServiceType")
    BookingServiceEntity toEntity(BookingService dto, Long bookingId,
            EBookingServiceStatus bookingServiceStatus, EBookingServiceType bookingServiceType);

    BookingService toDto(BookingServiceInfo info, List<BookingResult> bookingResults);

    BookingService toDto(BookingServiceEntity entity);

    BookingServiceEntity toEntity(BookingServiceInfo info);
}
