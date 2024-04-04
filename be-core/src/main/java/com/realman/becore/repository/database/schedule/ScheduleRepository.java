package com.realman.becore.repository.database.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realman.becore.dto.schedule.ScheduleInfo;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findByStaffId(Long staffId);

    @Query("""
            SELECT
                sc.scheduleId AS scheduleId,
                sc.staffId AS staffId,
                sc.shiftId AS shiftId,
                s.shift AS shift,
                sc.workingDate AS workingDate,
                sc.scheduleStatus AS scheduleStatus,
                s.startShift AS startShift,
                s.endShift AS endShift
            FROM ScheduleEntity sc
            INNER JOIN ShiftEntity s ON s.shiftId = sc.shiftId
            INNER JOIN StaffEntity st ON st.staffId = sc.staffId
            WHERE sc.staffId = :staffId
            """)
    List<ScheduleInfo> findInfoByStaffId(Long staffId);
}
