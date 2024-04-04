package com.realman.becore.controller.api.schedule;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.schedule.models.ScheduleResponse;
import com.realman.becore.util.response.ListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Schedule", description = "schedule management API")
@RequestMapping("/v1/schedule/{staffId}")
@PreAuthorize("hasAuthority('schedule:view')")
public interface ScheduleAPI {
    @GetMapping
    ListResponse<ScheduleResponse> findByStaffId(@PathVariable Long staffId);
}
