package com.realman.becore.repository.database.staff;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.realman.becore.dto.staff.StaffInfo;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    Optional<StaffEntity> findByAccountId(Long accountId);

    @Query("""
            SELECT
                s.staffId AS staffId,
                a.accountId AS accountId,
                sc.scheduleId AS scheduleId,
                sc.workingDate AS workingDate,
                sh.shift AS shift,
                sh.startShift AS startShift,
                sh.endShift AS endShift,
                b.bookingId AS bookingId,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointmentDate,
                bs.endAppointment AS endAppointment,
                bs.bookingServiceStatus AS bookingStatus,
                s.professional AS professional
            FROM StaffEntity s
            INNER JOIN AccountEntity a ON s.accountId = s.accountId
            LEFT JOIN ScheduleEntity sc ON s.staffId = sc.staffId
            INNER JOIN ShiftEntity sh ON sc.shiftId = sh.shiftId
            LEFT JOIN BookingServiceEntity bs ON s.staffId = bs.staffId
                AND (bs.bookingServiceStatus = 0
                    OR bs.bookingServiceStatus = 1)
            LEFT JOIN RatingEntity r ON r.staffId = s.staffId
            LEFT JOIN BookingEntity b ON bs.bookingId = b.bookingId
            WHERE s.accountId = :accountId
            """)
    List<StaffInfo> findInfosById(Long accountId);
}
