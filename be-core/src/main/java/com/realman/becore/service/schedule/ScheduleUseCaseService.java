package com.realman.becore.service.schedule;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.enums.EScheduleStatus;
import com.realman.becore.dto.schedule.Schedule;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleUseCaseService {
    @NonNull
    private final ScheduleCommandService scheduleCommandService;
    @NonNull
    private final ScheduleQueryService scheduleQueryService;

    @Transactional
    public void save(List<Schedule> schedules) {
        scheduleCommandService.save(schedules);
    }

    @Transactional
    public void updateSchedule(Long accountId, List<Schedule> schedules) {
        scheduleCommandService.updateSchedule(accountId, schedules);
    }

    @Transactional
    public void updateStatus(Long scheduleId, EScheduleStatus scheduleStatus) {
        scheduleCommandService.updateStatus(scheduleId, scheduleStatus);
    }

    public List<Schedule> findById(Long staffId) {
        return scheduleQueryService.findById(staffId);
    }
}
