package com.realman.becore.controller.api.rating.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.rating.Rating;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingModelMapper {
    Rating toDto(RatingRequest model);
}
