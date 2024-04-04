package com.realman.becore.controller.api.schedule.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.schedule.Schedule;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleModelMapper {
    Schedule toDto(ScheduleRequest model);

    ScheduleResponse toModel(Schedule dto);
}
