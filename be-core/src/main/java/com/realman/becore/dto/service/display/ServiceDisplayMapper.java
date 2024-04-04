package com.realman.becore.dto.service.display;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.service.display.ServiceDisplayEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceDisplayMapper {
    @Mapping(source = "serviceId", target = "serviceId")
    ServiceDisplayEntity toEntity(ServiceDisplay dto, Long serviceId);

    ServiceDisplay toDto(ServiceDisplayEntity entity);
}
