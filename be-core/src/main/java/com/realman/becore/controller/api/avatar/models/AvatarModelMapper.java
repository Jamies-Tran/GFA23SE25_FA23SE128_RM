package com.realman.becore.controller.api.avatar.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.avatar.Avatar;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvatarModelMapper {
    Avatar toDto(AvatarRequest model);

    AvatarResponse toModel(Avatar dto);
}
