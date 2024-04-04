package com.realman.becore.dto.staff.booking;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.dto.booking.BookingInfo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingStaffMapper {

    BookingStaff toDto(BookingInfo info);
}
