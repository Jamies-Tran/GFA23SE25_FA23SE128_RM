package com.realman.becore.controller.api.schedule;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.schedule.models.ScheduleRequest;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Schedule", description = "schedule management API")
@RequestMapping("/v1/schedules")
@PreAuthorize("hasAnyAuthority({'schedule:add', 'schedule:view'})")
public interface SchedulesAPI {
    @PostMapping
    void save(@RequestParam(required = false) Long staffId, @RequestBody List<ScheduleRequest> scheduleRequestList);
}
