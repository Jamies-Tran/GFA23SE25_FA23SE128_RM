package com.realman.becore.dto.schedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.enums.EScheduleStatus;
import com.realman.becore.dto.staff.StaffInfo;
import com.realman.becore.repository.database.schedule.ScheduleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScheduleMapper {
    @Mapping(source = "staffId", target = "staffId")
    @Mapping(source = "scheduleStatus", target = "scheduleStatus")
    ScheduleEntity toEntity(Schedule dto, Long staffId, EScheduleStatus scheduleStatus);

    @Mapping(source = "staffId", target = "staffId")
    ScheduleEntity toEntity(Schedule dto, Long staffId);

    Schedule toDto(ScheduleInfo info);

    Schedule toDto(ScheduleEntity entity);

    Schedule fromStaffInfo(StaffInfo info);
}
