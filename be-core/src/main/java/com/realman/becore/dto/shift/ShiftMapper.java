package com.realman.becore.dto.shift;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.shift.ShiftEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShiftMapper {
    ShiftEntity toEntity(Shift dto);

    Shift toDto(ShiftEntity entity);
}
