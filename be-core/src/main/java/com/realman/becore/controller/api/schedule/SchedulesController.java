package com.realman.becore.controller.api.schedule;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.realman.becore.controller.api.schedule.models.ScheduleModelMapper;
import com.realman.becore.controller.api.schedule.models.ScheduleRequest;
import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.service.schedule.ScheduleUseCaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SchedulesController implements SchedulesAPI {
    @NonNull
    private final ScheduleUseCaseService scheduleUseCaseService;
    @NonNull
    private final ScheduleModelMapper scheduleModelMapper;

    @Override
    public void save(Long staffId, List<ScheduleRequest> scheduleRequestList) {
        List<Schedule> schedules = scheduleRequestList.stream().map(scheduleModelMapper::toDto).toList();
        scheduleUseCaseService.save(schedules);
    }

}
