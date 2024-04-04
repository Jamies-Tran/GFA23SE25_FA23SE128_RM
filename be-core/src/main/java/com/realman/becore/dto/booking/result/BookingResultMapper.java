package com.realman.becore.dto.booking.result;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.repository.database.booking.result.BookingResultEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingResultMapper {
    BookingResultEntity toEntity(BookingResult dto);

    BookingResult toDto(BookingResultEntity entity);
}
