package com.realman.becore.dto.itimacy.level;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.itimacy.level.ItimacyLevelEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItimacyLevelMapper {
    ItimacyLevelEntity toEntity(ItimacyLevel dto);

    ItimacyLevel toDto(ItimacyLevelEntity entity);
}
