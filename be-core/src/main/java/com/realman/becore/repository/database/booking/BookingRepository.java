package com.realman.becore.repository.database.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realman.becore.dto.booking.BookingInfo;
import com.realman.becore.dto.booking.BookingSearchCriteria;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @Query("""
            SELECT
                b.bookingId AS bookingId,
                a.accountId AS accountId,
                be.branchId AS branchId,
                b.bookingCode AS bookingCode,
                CONCAT(a.firstName, ' ', a.lastName) AS bookingOwnerName,
                a.phone AS bookingOwnerPhone,
                be.branchName AS branchName,
                be.branchAddress AS branchAddress,
                b.appointmentDate AS appointmentDate,
                b.bookingStatus AS bookingStatus,
                SUM(bsr.branchServicePrice) AS totalBookingPrice
            FROM BookingEntity b
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            INNER JOIN AccountEntity a ON cu.accountId = a.accountId
            INNER JOIN BookingServiceEntity bs ON b.bookingId = bs.bookingId
            INNER JOIN ShopServiceEntity sh ON bs.serviceId = sh.serviceId
            INNER JOIN BranchEntity be ON be.branchId = b.branchId
            INNER JOIN BranchServiceEntity bsr ON be.branchId = bsr.branchId AND sh.serviceId = bsr.serviceId
            WHERE b.bookingId = :bookingId
            """)
    Optional<BookingInfo> findInfoById(Long bookingId);

    @Query("""
            SELECT
                b.bookingId AS bookingId,
                cu.customerId AS customerId,
                be.branchId AS branchId,
                b.bookingCode AS bookingCode,
                CONCAT(a.firstName, ' ', a.lastName) AS bookingOwnerName,
                a.phone AS bookingOwnerPhone,
                be.branchName AS branchName,
                be.branchAddress AS branchAddress,
                b.appointmentDate AS appointmentDate,
                b.bookingStatus AS bookingStatus
            FROM BookingEntity b
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            INNER JOIN AccountEntity a ON cu.accountId = a.accountId
            INNER JOIN BranchEntity be ON be.branchId = b.branchId
            WHERE :#{#searchCriteria.hasBranchIdEmpty()} = TRUE OR b.branchId = :#{#searchCriteria.branchId}
                AND :#{#searchCriteria.hasCustomerIdEmpty()} = TRUE OR cu.customerId = :#{#searchCriteria.customerId}
            """)
    Page<BookingInfo> findAllInfo(BookingSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
            SELECT
                b.bookingId AS bookingId,
                bs.bookingServiceId AS bookingServiceId,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.bookingServiceStatus AS bookingServiceStatus
            FROM BookingEntity b
            INNER JOIN BookingServiceEntity bs ON b.bookingId = bs.bookingId
            INNER JOIN BranchServiceEntity bsr ON b.branchId = bsr.branchId
            WHERE bs.staffId = :staffId
            """)
    List<BookingInfo> findInfoByStaffId(Long staffId);

    @Query("""
            SELECT
                b.bookingId AS bookingId,
                cu.customerId AS customerId,
                a.accountId AS accountId,
                be.branchId AS branchId,
                b.bookingCode AS bookingCode,
                CONCAT(a.firstName, ' ', a.lastName) AS bookingOwnerName,
                a.phone AS bookingOwnerPhone,
                be.branchName AS branchName,
                be.branchAddress AS branchAddress,
                b.appointmentDate AS appointmentDate,
                b.bookingStatus AS bookingStatus,
                SUM(bsr.branchServicePrice) AS totalBookingPrice
            FROM BookingEntity b
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            INNER JOIN AccountEntity a ON cu.accountId = a.accountId
            INNER JOIN BookingServiceEntity bs ON b.bookingId = bs.bookingId
            INNER JOIN ShopServiceEntity sh ON bs.serviceId = sh.serviceId
            INNER JOIN BranchEntity be ON be.branchId = b.branchId
            INNER JOIN BranchServiceEntity bsr ON be.branchId = bsr.branchId
            WHERE bs.bookingServiceId = :bookingServiceId
            """)
    Optional<BookingInfo> findByBookingServiceId(Long bookingServiceId);

    @Query("""
            SELECT
                CASE WHEN COUNT(bs) > 1 THEN
                    CASE WHEN bs.bookingServiceId != :bookingServiceId THEN
                        CASE WHEN bs.bookingServiceStatus != 1 THEN FALSE
                            ELSE TRUE
                        ENd
                    END
                    ELSE CASE WHEN bs.bookingServiceStatus != 1 THEN FALSE
                        ELSE TRUE
                    END
                END
            FROM BookingEntity b
            INNER JOIN BookingServiceEntity bs ON b.bookingId = bs.bookingId
            WHERE b.bookingId = :bookingId
            GROUP BY bs
            """)
    List<Boolean> hasBookingContainBookingServiceStatus(Long bookingServiceId, Long bookingId);

    @Query("""
            SELECT DISTINCT
                bsr.branchServicePrice
            FROM BookingEntity b
            INNER JOIN BookingServiceEntity bs ON b.bookingId = bs.bookingId
            INNER JOIN BranchServiceEntity bsr ON bs.serviceId = bsr.serviceId
            WHERE b.bookingId = :bookingId AND bs.bookingServiceStatus != 7
            GROUP BY bsr.branchServicePrice
            """)
    List<Long> findBookingPrice(Long bookingId);
}
