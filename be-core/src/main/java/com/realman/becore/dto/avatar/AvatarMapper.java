package com.realman.becore.dto.avatar;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.avatar.AvatarEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvatarMapper {
    @Mapping(source = "staffId", target = "staffId")
    AvatarEntity toEntity(Avatar dto, Long staffId);

    Avatar toDto(AvatarEntity entity);

    @Mapping(target = "entity.avatarId", ignore = true)
    void updateEntity(@MappingTarget AvatarEntity entity, Avatar dto);
}
