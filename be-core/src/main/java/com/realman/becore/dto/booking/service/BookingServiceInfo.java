package com.realman.becore.dto.booking.service;

import java.time.LocalTime;

import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EProfessional;

import java.time.Duration;

public interface BookingServiceInfo {
    void setBookingServiceId(Long bookingServiceId);

    void setBookingId(Long bookingId);

    void setServiceId(Long serviceId);

    void setStaffId(Long staffId);

    void setCategoryId(Long categoryId);

    void setCategoryName(String categoryName);

    void setPriority(Integer priority);

    void setServicePrice(Long servicePrice);

    void setServiceName(String serviceName);

    void setStaffName(String staffName);

    void setStaffPhone(String staffPhone);

    void setStartTime(LocalTime startTime);

    void setEndTime(LocalTime endTime);

    void setActualStartTime(LocalTime actualStartTime);

    void setActualEndTime(LocalTime actualEndTime);

    void setDuration(Duration duration);

    void setBookingServiceStatus(EBookingServiceStatus bookingServiceStatus);

    void setAllowUpdate(Boolean allowUpdate);

    void setCusPhone(String cusPhone);

    void setProfessional(EProfessional professional);

    Long getBookingServiceId();

    Long getBookingId();

    Long getServiceId();

    Long getStaffId();

    Long getCategoryId();

    String getCategoryName();

    Integer getPriority();

    Long getServicePrice();

    String getServiceName();

    String getStaffName();

    String getStaffPhone();

    LocalTime getStartTime();

    LocalTime getEndTime();

    LocalTime getActualStartTime();

    LocalTime getActualEndTime();

    Duration getDuration();

    EBookingServiceStatus getBookingServiceStatus();

    Boolean getAllowUpdate();

    String getCusPhone();

    EProfessional getProfessional();
}