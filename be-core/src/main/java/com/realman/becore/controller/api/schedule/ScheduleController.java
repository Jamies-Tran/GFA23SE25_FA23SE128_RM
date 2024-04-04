package com.realman.becore.controller.api.schedule;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.schedule.models.ScheduleModelMapper;
import com.realman.becore.controller.api.schedule.models.ScheduleResponse;
import com.realman.becore.dto.schedule.Schedule;
import com.realman.becore.service.schedule.ScheduleUseCaseService;
import com.realman.becore.util.response.ListResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleAPI {
    @NonNull
    private final ScheduleUseCaseService scheduleUseCaseService;
    @NonNull
    private final ScheduleModelMapper scheduleModelMapper;

    @Override
    public ListResponse<ScheduleResponse> findByStaffId(Long staffId) {
        List<Schedule> scheduleList = scheduleUseCaseService.findById(staffId);
        List<ScheduleResponse> responses = scheduleList.stream().map(scheduleModelMapper::toModel).toList();
        return new ListResponse<>(responses);
    }

}
