package com.realman.becore.repository.database.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.realman.becore.dto.booking.service.BookingServiceInfo;
import com.realman.becore.dto.booking.service.BookingServiceSearchCriteria;
import com.realman.becore.dto.enums.EBookingServiceStatus;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingServiceEntity, Long> {
    @Query("""
            SELECT
                bs.bookingServiceId AS bookingServiceId,
                bs.bookingId AS bookingId,
                ss.serviceId AS serviceId,
                s.staffId AS staffId,
                r.ratingId AS ratingId,
                b.bookingCode As bookingCode,
                s.professional AS professional,
                c.categoryId AS categoryId,
                c.categoryName AS categoryName,
                c.priority AS priority,
                COALESCE(brs.branchServicePrice, ss.servicePrice) AS servicePrice,
                ss.serviceName AS serviceName,
                CONCAT(a.firstName, ' ', a.lastName) AS staffName,
                a.phone AS staffPhone,
                CONCAT(ac.firstName, ' ', ac.lastName) AS customerName,
                ac.phone AS customerPhone,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.actualStartAppointment AS actualStartAppointment,
                bs.actualEndAppointment AS actualEndAppointment,
                (bs.actualEndAppointment - bs.actualStartAppointment) AS duration,
                bs.bookingServiceStatus AS bookingServiceStatus,
                bs.bookingServiceType AS bookingServiceType,
                (a.accountId = :accountId) AS allowUpdate
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            LEFT JOIN StaffEntity s ON bs.staffId = s.staffId
            LEFT JOIN AccountEntity a ON a.accountId = s.accountId
            INNER JOIN AccountEntity ac ON cu.accountId = ac.accountId
            INNER JOIN ShopServiceEntity ss ON ss.serviceId = bs.serviceId
            INNER JOIN CategoryEntity c ON c.categoryId = ss.categoryId
            INNER JOIN BranchServiceEntity brs ON ss.serviceId = brs.serviceId
            LEFT JOIN RatingEntity r ON bs.ratingId = r.ratingId
            WHERE bs.bookingId = :bookingId
            ORDER BY priority ASC
            """)
    List<BookingServiceInfo> findInfoByBookingId(Long bookingId, Long accountId);

    @Query("""
            SELECT
                bs.bookingServiceId AS bookingServiceId,
                bs.bookingId AS bookingId,
                ss.serviceId AS serviceId,
                s.staffId AS staffId,
                r.ratingId AS ratingId,
                b.bookingCode AS bookingCode,
                s.professional AS professional,
                c.categoryId AS categoryId,
                c.categoryName AS categoryName,
                c.priority AS priority,
                COALESCE(brs.branchServicePrice, ss.servicePrice) AS servicePrice,
                ss.serviceName AS serviceName,
                CONCAT(a.firstName, ' ', a.lastName) AS staffName,
                a.phone AS staffPhone,
                CONCAT(ac.firstName, ' ', ac.lastName) AS customerName,
                ac.phone AS customerPhone,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.actualStartAppointment AS actualStartAppointment,
                bs.actualEndAppointment AS actualEndAppointment,
                (bs.actualEndAppointment - bs.actualStartAppointment) AS duration,
                (a.accountId = :accountId) AS allowUpdate,
                bs.bookingServiceStatus AS bookingServiceStatus,
                bs.bookingServiceType AS bookingServiceType
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            LEFT JOIN StaffEntity s ON s.staffId = bs.staffId
            LEFT JOIN AccountEntity a ON a.accountId = s.accountId
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            INNER JOIN AccountEntity ac ON cu.accountId = ac.accountId
            INNER JOIN ShopServiceEntity ss ON ss.serviceId = bs.serviceId
            INNER JOIN CategoryEntity c ON c.categoryId = ss.categoryId
            INNER JOIN BranchServiceEntity brs ON ss.serviceId = brs.serviceId
            LEFT JOIN RatingEntity r ON r.ratingId = bs.ratingId
            ORDER BY priority ASC
            """)
    List<BookingServiceInfo> findAllInfo(Long accountId);

    @Query("""
            SELECT
                bs.bookingServiceId AS bookingServiceId,
                bs.bookingId AS bookingId,
                ss.serviceId AS serviceId,
                s.staffId AS staffId,
                r.ratingId AS ratingId,
                s.professional AS professional,
                c.categoryId AS categoryId,
                c.categoryName AS categoryName,
                c.priority AS priority,
                COALESCE(brs.branchServicePrice, ss.servicePrice) AS branchServicePrice,
                ss.serviceName AS serviceName,
                CONCAT(a.firstName, ' ', a.lastName) AS staffName,
                a.phone AS staffPhone,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.actualStartAppointment AS actualStartAppointment,
                bs.actualEndAppointment AS actualEndAppointment,
                (bs.actualEndAppointment - bs.actualStartAppointment) AS duration,
                (a.accountId = :accountId) AS allowUpdate,
                bs.bookingServiceStatus AS bookingServiceStatus,
                bs.bookingServiceType AS bookingServiceType
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            INNER JOIN StaffEntity s ON s.staffId = bs.staffId
            INNER JOIN AccountEntity a ON a.accountId = s.accountId
            INNER JOIN ShopServiceEntity ss ON ss.serviceId = bs.serviceId
            INNER JOIN CategoryEntity c ON c.categoryId = ss.categoryId
            INNER JOIN BranchServiceEntity brs ON ss.serviceId = brs.serviceId
            LEFT JOIN RatingEntity r ON r.ratingId = bs.ratingId
            WHERE bs.bookingServiceId = :bookingServiceId
            """)
    Optional<BookingServiceInfo> findInfoById(Long bookingServiceId, Long accountId);

    @Query("""
            SELECT bs
            FROM BookingServiceEntity bs WHERE bs.bookingServiceId != :bookingServiceId AND bs.bookingId = :bookingId
            """)
    List<BookingServiceEntity> findAllNotEqualId(Long bookingId, Long bookingServiceId);

    @Query("""
            SELECT bs
            FROM BookingServiceEntity bs
            WHERE bs.bookingId = :bookingId AND bs.bookingServiceId != :currentBookingServiceId
            """)
    List<BookingServiceEntity> findOtherByBookingId(Long bookingId, Long currentBookingServiceId);

    @Query("""
            SELECT
                bs.bookingServiceId AS bookingServiceId,
                bs.bookingId AS bookingId,
                ss.serviceId AS serviceId,
                s.staffId AS staffId,
                r.ratingId AS ratingId,
                s.professional AS professional,
                c.categoryId AS categoryId,
                c.categoryName AS categoryName,
                c.priority AS priority,
                COALESCE(brs.branchServicePrice, ss.servicePrice) AS servicePrice,
                ss.serviceName AS serviceName,
                CONCAT(a.firstName, ' ', a.lastName) AS staffName,
                a.phone AS staffPhone,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.actualStartAppointment AS actualStartAppointment,
                bs.actualEndAppointment AS actualEndAppointment,
                (bs.actualEndAppointment - bs.actualStartAppointment) AS duration,
                (s.staffId = :staffId) AS allowUpdate,
                bs.bookingServiceType AS bookingServiceType,
                bs.bookingServiceStatus AS bookingServiceStatus
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            INNER JOIN StaffEntity s ON s.staffId = bs.staffId
            INNER JOIN AccountEntity a ON a.accountId = s.accountId
            INNER JOIN ShopServiceEntity ss ON ss.serviceId = bs.serviceId
            INNER JOIN CategoryEntity c ON c.categoryId = ss.categoryId
            INNER JOIN BranchServiceEntity brs ON ss.serviceId = brs.serviceId
            LEFT JOIN RatingEntity r ON r.ratingId = bs.ratingId
            WHERE bs.staffId = :staffId
            """)
    Page<BookingServiceInfo> findByStaffId(Long staffId, Pageable pageable);

    @Query("""
            SELECT
                bs.bookingServiceId AS bookingServiceId,
                bs.bookingId AS bookingId,
                ss.serviceId AS serviceId,
                s.staffId AS staffId,
                r.ratingId AS ratingId,
                s.professional AS professional,
                c.categoryId AS categoryId,
                c.categoryName AS categoryName,
                b.bookingCode AS bookingCode,
                c.priority AS priority,
                COALESCE(brs.branchServicePrice, ss.servicePrice) AS servicePrice,
                ss.serviceName AS serviceName,
                CONCAT(a.firstName, ' ', a.lastName) AS staffName,
                a.phone AS staffPhone,
                CONCAT(ac.firstName, ' ', ac.lastName) AS customerName,
                ac.phone AS customerPhone,
                b.appointmentDate AS appointmentDate,
                bs.startAppointment AS startAppointment,
                bs.endAppointment AS endAppointment,
                bs.actualStartAppointment AS actualStartAppointment,
                bs.actualEndAppointment AS actualEndAppointment,
                (bs.actualEndAppointment - bs.actualStartAppointment) AS duration,
                bs.bookingServiceType AS bookingServiceType,
                bs.bookingServiceStatus AS bookingServiceStatus
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            INNER JOIN CustomerEntity cu ON b.customerId = cu.customerId
            LEFT JOIN StaffEntity s ON s.staffId = bs.staffId
            LEFT JOIN AccountEntity a ON a.accountId = s.accountId
            LEFT JOIN AccountEntity ac ON cu.accountId = ac.accountId
            INNER JOIN ShopServiceEntity ss ON ss.serviceId = bs.serviceId
            INNER JOIN CategoryEntity c ON c.categoryId = ss.categoryId
            INNER JOIN BranchServiceEntity brs ON ss.serviceId = brs.serviceId
            LEFT JOIN RatingEntity r ON r.ratingId = bs.ratingId
            WHERE (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE OR brs.branchId = :#{#searchCriteria.branchId()})
                AND (:#{#searchCriteria.hasStatusEmpty()} = TRUE OR bs.bookingServiceStatus = :#{#searchCriteria.bookingServiceStatus()})
                AND (:#{#searchCriteria.hasTypeEmpty()} = TRUE OR bs.bookingServiceType = :#{#searchCriteria.bookingServiceType()})
                AND (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                    OR (LOWER(b.bookingCode) LIKE %:#{#searchCriteria.search()}%)
                    OR (CONCAT(ac.firstName, ' ', ac.lastName) LIKE %:#{#searchCriteria.search()}%)
                    OR (ac.phone LIKE %:#{#searchCriteria.search()}%)
                    OR (CONCAT(a.firstName, ' ', a.lastName) LIKE %:#{#searchCriteria.search()}%)
                    OR (a.phone LIKE %:#{#searchCriteria.search()}%))
            """)
    Page<BookingServiceInfo> findAll(BookingServiceSearchCriteria searchCriteria, Pageable pageable);

    List<BookingServiceEntity> findAllByBookingId(Long bookingId);

    Boolean existsByBookingServiceStatus(EBookingServiceStatus bookingServiceStatus);
}
