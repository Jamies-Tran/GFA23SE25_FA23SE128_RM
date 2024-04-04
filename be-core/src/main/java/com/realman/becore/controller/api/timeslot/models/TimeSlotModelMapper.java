package com.realman.becore.controller.api.timeslot.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.timeslot.TimeSlot;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TimeSlotModelMapper {
    TimeSlotResponse toModel(TimeSlot dto);
}
