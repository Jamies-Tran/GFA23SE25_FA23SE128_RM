package com.realman.becore.dto.staff;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.branch.BranchInfo;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.dto.staff.booking.BookingStaff;
import com.realman.becore.repository.database.staff.StaffEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {
    Staff toDto(StaffEntity entity);

    @Mapping(source = "professional", target = "professional")
    StaffEntity toEntity(Staff dto, EProfessional professional);

    Staff toDto(StaffEntity entity, List<Schedule> scheduleList);

    Staff fromBranchInfo(BranchInfo branchInfo, List<Schedule> scheduleList);

    Staff toDto(StaffEntity entity, List<Schedule> scheduleList, List<BookingStaff> bookingList, Double averageRating);
}
