package com.realman.becore.dto.rating;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.rating.RatingEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper {

    @Mapping(target = "customerId", source = "customerId")
    RatingEntity toEntity(Rating dto, Long customerId);

    Rating toDto(RatingEntity entity);

    @Mapping(target = "foundEntity.ratingId", ignore = true)
    void updateEntity(@MappingTarget RatingEntity foundEntity, Rating dto);
}
