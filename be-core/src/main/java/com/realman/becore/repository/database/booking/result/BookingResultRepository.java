package com.realman.becore.repository.database.booking.result;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingResultRepository extends JpaRepository<BookingResultEntity, Long> {
    @Query("""
            SELECT br
            FROM BookingResultEntity br
            WHERE br.bookingServiceId = :bookingServiceId
            """)
    List<BookingResultEntity> findByBookingId(Long bookingServiceId);
}
