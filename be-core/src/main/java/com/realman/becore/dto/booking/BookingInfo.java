package com.realman.becore.dto.booking;

import java.time.LocalDate;
import java.time.LocalTime;

import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingStatus;

public interface BookingInfo {
    void setBookingId(Long bookingId);

    void setBookingServiceId(Long bookingServiceId);

    void setCustomerId(Long customerId);

    void setBranchId(Long branchId);

    void setBookingCode(String bookingCode);

    void setBookingOwnerName(String bookingOwnerName);

    void setBookingOwnerPhone(String bookingOwnerPhone);

    void setBranchAddress(String branchAddress);

    void setBranchName(String branchName);

    void setAppointmentDate(LocalDate appointmentDate);

    void setStartAppointment(LocalTime startAppointment);

    void setEndAppointment(LocalTime endAppointment);

    void setBookingStatus(EBookingStatus bookingStatus);

    void setBookingServiceStatus(EBookingServiceStatus bookingServiceStatus);

    void setTotalBookingPrice(Long totalBookingPrice);

    Long getBookingId();

    Long getBookingServiceId();

    Long getCustomerId();

    Long getBranchId();

    String getBookingCode();

    String getBookingOwnerName();

    String getBookingOwnerPhone();

    String getBranchAddress();

    String getBranchName();

    LocalDate getAppointmentDate();

    LocalTime getStartAppointment();

    LocalTime getEndAppointment();

    EBookingStatus getBookingStatus();

    EBookingServiceStatus getBookingServiceStatus();

    Long getTotalBookingPrice();
}
