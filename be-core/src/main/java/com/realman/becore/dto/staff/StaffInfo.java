package com.realman.becore.dto.staff;

import java.time.LocalDate;
import java.time.LocalTime;

import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingStatus;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.EShift;

public interface StaffInfo {
    void setStaffId(Long staffId);

    void setAccountId(Long accountId);

    void setSheduleId(Long scheduleId);

    void setWorkingDate(LocalDate workingDate);

    void setShift(EShift shift);

    void setStartShift(LocalTime startShift);

    void setEndShift(LocalTime endShift);

    void setBookingId(Long bookingId);

    void setBookingServiceId(Long bookingServiceId);

    void setAppointmentDate(LocalDate appointmentDate);

    void setStartAppointment(LocalTime startAppointment);

    void setEndAppointment(LocalTime endAppointment);

    void setBookingServiceStatus(EBookingStatus bookingServiceStatus);

    void setProfessional(EProfessional professional);

    void setAverageRating(Double averageRating);

    Long getStaffId();

    Long getAccountId();

    Long getSheduleId();

    LocalDate getWorkingDate();

    EShift getShift();

    LocalTime getStartShift();

    LocalTime getEndShift();

    Long getBookingId();

    LocalDate getAppointmentDate();

    LocalTime getStartAppointment();

    LocalTime getEndAppointment();

    EBookingServiceStatus getBookingServiceStatus();

    EProfessional getProfessional();

    Double getAverageRating();

}
