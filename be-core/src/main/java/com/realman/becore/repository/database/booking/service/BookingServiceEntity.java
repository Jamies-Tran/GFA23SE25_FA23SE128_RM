package com.realman.becore.repository.database.booking.service;

import java.time.LocalTime;

import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_service")
public class BookingServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingServiceId;
    private Long bookingId;
    private Long serviceId;
    private Long staffId;
    private Long ratingId;
    @Temporal(TemporalType.TIME)
    private LocalTime startAppointment;
    @Temporal(TemporalType.TIME)
    private LocalTime actualStartAppointment;
    @Temporal(TemporalType.TIME)
    private LocalTime endAppointment;
    @Temporal(TemporalType.TIME)
    private LocalTime actualEndAppointment;
    private EBookingServiceStatus bookingServiceStatus;
    private EBookingServiceType bookingServiceType;
}
