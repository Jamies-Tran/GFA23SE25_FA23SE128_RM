package com.realman.becore.repository.database.booking.result;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_result")
public class BookingResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingResultId;

    @Column(name = "booking_service_id", nullable = false)
    private Long bookingServiceId;

    @Column(name = "booking_result_image", nullable = false)
    private String bookingResultImg;
}
