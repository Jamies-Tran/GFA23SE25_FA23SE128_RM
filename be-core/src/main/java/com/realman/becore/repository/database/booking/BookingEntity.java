package com.realman.becore.repository.database.booking;

import java.io.Serializable;
import java.time.LocalDate;
import com.realman.becore.dto.enums.EBookingStatus;
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
@Table(name = "booking")
public class BookingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long customerId;
    private Long branchId;
    private String bookingCode;
    @Temporal(TemporalType.DATE)
    private LocalDate appointmentDate;
    private EBookingStatus bookingStatus;

}
