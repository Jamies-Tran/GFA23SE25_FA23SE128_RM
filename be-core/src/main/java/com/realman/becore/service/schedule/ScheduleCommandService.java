package com.realman.becore.service.schedule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.enums.EScheduleStatus;
import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.dto.schedule.ScheduleMapper;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.schedule.ScheduleEntity;
import com.realman.becore.repository.database.schedule.ScheduleRepository;
import com.realman.becore.service.staff.StaffUsecaseService;
import com.realman.becore.util.RequestContext;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleCommandService {
    @NonNull
    private final ScheduleRepository scheduleRepository;
    @NonNull
    private final ScheduleMapper scheduleMapper;
    @NonNull
    private final StaffUsecaseService staffUsecaseService;
    @NonNull
    private final RequestContext requestContext;

    public void save(List<Schedule> schedules) {
        Staff staff = staffUsecaseService.findById(requestContext.getStaffId());
        List<ScheduleEntity> scheduleList = schedules.stream()
                .map(schedule -> scheduleMapper.toEntity(schedule, staff.staffId(), EScheduleStatus.ONGOING)).toList();
        scheduleRepository.saveAll(scheduleList);
    }

    public void updateSchedule(Long staffId, List<Schedule> schedules) {
        Staff staff = staffUsecaseService.findById(staffId);
        List<ScheduleEntity> secheduleList = scheduleRepository.findByStaffId(staff.staffId());
        scheduleRepository.deleteAll(secheduleList);
        List<ScheduleEntity> updatedScheduleList = schedules.stream()
                .map(schedule -> scheduleMapper.toEntity(schedule, staff.staffId())).toList();
        scheduleRepository.saveAll(updatedScheduleList);
    }

    public void updateStatus(Long scheduleId, EScheduleStatus scheduleStatus) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleId)
                .orElseThrow(ResourceNotFoundException::new);
        scheduleEntity.setScheduleStatus(scheduleStatus);
        scheduleRepository.save(scheduleEntity);
    }
}
