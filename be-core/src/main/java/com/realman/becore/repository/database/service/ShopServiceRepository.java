package com.realman.becore.repository.database.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.realman.becore.controller.api.services.models.BranchId;
import com.realman.becore.dto.service.ShopServiceInfo;
import com.realman.becore.dto.service.ShopServiceSearchCriteria;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopServiceEntity, Long> {

        @Query("""
                        SELECT
                            s.serviceId AS serviceId,
                            c.categoryId AS categoryId,
                            c.categoryName AS categoryName,
                            s.serviceName AS serviceName,
                            s.serviceDescription AS serviceDescription,
                            b.branchId AS branchId,
                            b.branchName AS branchName,
                            bs.branchServicePrice AS branchServicePrice,
                            s.servicePrice AS servicePrice,
                            s.durationValue AS durationValue,
                            s.durationTime AS durationTime,
                            s.durationText AS durationText
                        FROM ShopServiceEntity s
                        LEFT JOIN BranchServiceEntity bs ON bs.serviceId = s.serviceId
                        LEFT JOIN BranchEntity b ON b.branchId = bs.branchId
                        INNER JOIN CategoryEntity c ON c.categoryId = s.categoryId
                        WHERE (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                                OR LOWER(s.serviceName) LIKE %:#{#searchCriteria.search}%)
                                AND (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE
                                        OR b.branchId = :#{#searchCriteria.branchId})
                        """)
        Page<ShopServiceInfo> findAllInfo(ShopServiceSearchCriteria searchCriteria, Pageable pageable);

        @Query("""
                        SELECT
                            s.serviceId AS serviceId,
                            c.categoryId AS categoryId,
                            c.categoryName AS categoryName,
                            s.serviceName AS serviceName,
                            s.serviceDescription AS serviceDescription,
                            s.servicePrice AS servicePrice,
                            s.durationValue AS durationValue,
                            s.durationTime AS durationTime,
                            s.durationText AS durationText
                        FROM ShopServiceEntity s
                        INNER JOIN CategoryEntity c ON c.categoryId = s.categoryId
                        WHERE s.serviceId = :serviceId
                        """)
        Optional<ShopServiceInfo> findInfoById(Long serviceId);

        @Query("""
                        SELECT
                            s.serviceId AS serviceId,
                            c.categoryId AS categoryId,
                            c.categoryName AS categoryName,
                            s.serviceName AS serviceName,
                            s.serviceDescription AS serviceDescription,
                            b.branchId AS branchId,
                            b.branchName AS branchName,
                            bs.branchServicePrice AS branchServicePrice,
                            s.servicePrice AS servicePrice,
                            s.durationValue AS durationValue,
                            s.durationTime AS durationTime,
                            s.durationText AS durationText
                        FROM ShopServiceEntity s
                        INNER JOIN BranchServiceEntity bs ON bs.serviceId = s.serviceId
                        INNER JOIN BranchEntity b ON b.branchId = bs.branchId
                        INNER JOIN CategoryEntity c ON c.categoryId = s.categoryId
                        WHERE s.serviceId = :serviceId
                        """)
        List<ShopServiceInfo> findServiceId(Long serviceId);

        @Query("""
                        SELECT s
                        FROM ShopServiceEntity s
                        WHERE s.categoryId = :categoryId
                        """)
        List<ShopServiceEntity> findByCategoryId(Long categoryId);

        @Query("""
                        SELECT DISTINCT
                            s.serviceId AS serviceId,
                            s.serviceName AS serviceName,
                            s.servicePrice AS servicePrice,
                            s.durationValue AS durationValue,
                            s.durationText AS durationText
                        FROM ShopServiceEntity s
                        LEFT JOIN BranchServiceEntity brs ON s.serviceId = brs.serviceId
                        WHERE :#{#branchId.hasValueEmpty()} = TRUE OR brs.branchId = :#{#branchId.value()}
                        """)
        List<ShopServiceInfo> findAllServiceField(BranchId branchId);
}
