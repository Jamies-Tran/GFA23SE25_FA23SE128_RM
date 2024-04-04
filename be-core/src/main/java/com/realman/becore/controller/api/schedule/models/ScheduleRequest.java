package com.realman.becore.controller.api.schedule.models;

import java.time.LocalDate;

public record ScheduleRequest(
                Long shiftId,
                LocalDate workingDate) {

}
