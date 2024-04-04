package com.realman.becore.repository.database.account;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.realman.becore.dto.account.AccountInfo;
import com.realman.becore.dto.account.AccountSearchCriteria;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByPhone(String phone);

    @Query("""
            SELECT
                a.accountId AS accountId,
                b.branchId AS branchId,
                s.staffId AS staffId,
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.gender AS gender,
                a.dob AS dob,
                a.phone AS phone,
                a.address AS address,
                a.status AS status,
                a.role AS role,
                b.branchName AS branchName,
                b.branchAddress AS branchAddress,
                s.professional AS professional
            FROM AccountEntity a
            INNER JOIN BranchEntity b ON b.branchId = a.branchId
            INNER JOIN StaffEntity s ON a.accountId = s.accountId
            WHERE a.accountId = :accountId
                """)
    Optional<AccountInfo> findStaffAccount(Long accountId);

    @Query("""
            SELECT
                a.accountId AS accountId,
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.gender AS gender,
                a.dob AS dob,
                a.phone AS phone,
                a.address AS address,
                a.status AS status,
                a.role AS role,
                il.itimacyLevel AS itimacyLevel
            FROM AccountEntity a
            INNER JOIN CustomerEntity c ON a.accountId = c.accountId
            INNER JOIN ItimacyEntity i ON c.customerId = i.customerId
            INNER JOIN ItimacyLevelEntity il ON i.itimacyLevelId = il.itimacyLevelId
            WHERE a.accountId = :accountId
            AND a.role = CUSTOMER
                """)
    Optional<AccountInfo> findCustomerAccount(Long accountId);

    @Query("""
            SELECT
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.gender AS gender,
                a.dob AS dob,
                a.phone AS phone,
                a.address AS address,
                a.status AS status,
                a.role AS role
            FROM AccountEntity a
            WHERE a.accountId = :accountId
                """)
    Optional<AccountInfo> findByAccountId(Long accountId);

    @Query("""
            SELECT
                a.accountId AS accountId,
                b.branchId AS branchId,
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.gender AS gender,
                a.dob AS dob,
                a.phone AS phone,
                a.address AS address,
                a.status AS status,
                a.role AS role,
                b.branchName AS branchName,
                b.branchAddress AS branchAddress
            FROM AccountEntity a
            INNER JOIN BranchEntity b ON b.branchId = a.branchId
            WHERE a.accountId = :accountId
            AND a.role = BRANCH_MANAGER
                """)
    Optional<AccountInfo> findManagerAccount(Long accountId);

    @Query("""
            SELECT
                a.accountId AS accountId,
                b.branchId AS branchId,
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.phone AS phone,
                a.address AS address,
                a.gender AS gender,
                a.dob AS dob,
                a.status AS status,
                b.branchName AS branchName,
                b.branchAddress AS branchAddress,
                a.role AS role
            FROM AccountEntity a
            INNER JOIN BranchEntity b ON a.branchId = b.branchId
            LEFT JOIN StaffEntity s ON s.accountId = a.accountId
            WHERE a.role = :#{#searchCriteria.role}
            AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR LOWER(a.firstName) IN :#{#searchCriteria.searches}
                OR LOWER(a.lastName) IN :#{#searchCriteria.searches}
                OR a.phone IN :#{#searchCriteria.searches}
                OR LOWER(a.address) IN :#{#searchCriteria.searches})
            AND (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE OR a.branchId = :#{#searchCriteria.branchId})
            """)
    Page<AccountInfo> findAll(AccountSearchCriteria searchCriteria,
            Pageable pageable);

    @Query("""
            SELECT
                a.accountId AS accountId,
                a.firstName AS firstName,
                a.lastName AS lastName,
                a.thumbnailUrl AS thumbnailUrl,
                a.phone AS phone,
                a.address AS address,
                a.gender AS gender,
                a.dob AS dob,
                a.status AS status,
                a.role AS role
            FROM AccountEntity a
            INNER JOIN StaffEntity s ON a.accountId = s.accountId
            LEFT JOIN ScheduleEntity sc ON s.staffId = sc.staffId
            LEFT JOIN ShiftEntity sh ON sc.shiftId = sh.shiftId
            LEFT JOIN BookingServiceEntity bs ON s.staffId = bs.staffId AND (bs.bookingServiceStatus = 0 OR bs.bookingServiceStatus = 1)
            LEFT JOIN BookingEntity b ON bs.bookingId = b.bookingId
            WHERE a.branchId = :branchId
                AND b.appointmentDate = :appointmentDate
                    AND (:startAppointment BETWEEN sh.startShift AND sh.endShift
                        AND :startAppointment NOT BETWEEN bs.startAppointment AND bs.endAppointment AND :endAppointment NOT BETWEEN bs.startAppointment AND bs.endAppointment
                        AND CASE
                                WHEN :startAppointment < bs.startAppointment THEN :endAppointment < bs.endAppointment
                                WHEN :endAppointment > bs.endAppointment THEN :startAppointment > bs.endAppointment
                            END)
            GROUP BY a
            """)
    Page<AccountInfo> findSuitableForBooking(Long branchId, LocalDate appointmentDate, LocalTime startAppointment,
            LocalTime endAppointment, Pageable pageable);
}
