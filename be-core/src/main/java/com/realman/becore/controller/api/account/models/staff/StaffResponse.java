package com.realman.becore.controller.api.account.models.staff;

import com.realman.becore.controller.api.schedule.models.ScheduleResponse;
import com.realman.becore.dto.enums.EProfessional;

import java.util.List;
import lombok.Builder;

@Builder
public record StaffResponse(
                Long staffId,
                Double averageRating,
                List<ScheduleResponse> scheduleList,
                EProfessional professional) {

}
