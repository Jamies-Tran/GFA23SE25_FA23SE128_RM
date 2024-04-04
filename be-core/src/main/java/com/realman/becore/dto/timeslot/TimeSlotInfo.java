package com.realman.becore.dto.timeslot;

import java.time.LocalTime;

public interface TimeSlotInfo {
    void setTimeSlotId(Long timeSlotId);

    void setScheduleId(Long scheduleId);

    void setShiftId(Long shiftId);

    void setTime(LocalTime time);

    void setIsAvailable(Boolean isAvalable);

    Long getTimeSlotId();

    Long getScheduleId();

    Long getShiftId();

    LocalTime getTime();

    Boolean getIsAvailable();
}
