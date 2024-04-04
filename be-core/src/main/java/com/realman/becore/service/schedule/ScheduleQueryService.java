package com.realman.becore.service.schedule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.dto.schedule.ScheduleMapper;
import com.realman.becore.repository.database.schedule.ScheduleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleQueryService {
    @NonNull
    private final ScheduleRepository scheduleRepository;
    @NonNull
    private final ScheduleMapper scheduleMapper;

    public List<Schedule> findById(Long staffId) {
        List<Schedule> scheduleList = scheduleRepository.findInfoByStaffId(staffId).stream()
                .map(scheduleMapper::toDto).toList();

        return scheduleList;
    }

}
