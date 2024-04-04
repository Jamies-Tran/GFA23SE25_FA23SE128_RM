package com.realman.becore.repository.database.branch.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realman.becore.dto.branch.service.BranchServiceInfo;

@Repository
public interface BranchServiceRepository extends JpaRepository<BranchServiceEntity, Long> {

     @Query("""
               SELECT
                    s.serviceId AS serviceId,
                    b.branchId AS branchId,
                    s.serviceName AS serviceName,
                    b.branchName AS branchName,
                    b.branchThumbnailUrl AS branchThumbnailUrl,
                    bs.branchServicePrice AS branchServicePrice
               FROM BranchServiceEntity bs
               INNER JOIN ShopServiceEntity s ON s.serviceId = bs.serviceId
               INNER JOIN BranchEntity b ON b.branchId = bs.branchId
               WHERE bs.serviceId = :serviceId
               """)
     List<BranchServiceInfo> findAllByServiceId(Long serviceId);

     @Query("""
               SELECT
                    bs.branchServiceId AS branchServiceId,
                    s.serviceId AS serviceId,
                    b.branchId AS branchId,
                    s.serviceName AS serviceName,
                    b.branchName AS branchName,
                    b.branchThumbnailUrl AS branchThumbnailUrl,
                    bs.branchServicePrice AS branchServicePrice
               FROM BranchServiceEntity bs
               INNER JOIN ShopServiceEntity s ON s.serviceId = bs.serviceId
               INNER JOIN BranchEntity b ON b.branchId = bs.branchId
               WHERE bs.branchId = :branchId
               """)
     List<BranchServiceInfo> findAllByBranchId(Long branchId);

     @Query("""
               SELECT
                    bs.branchServiceId AS branchServiceId,
                    s.serviceId AS serviceId,
                    b.branchId AS branchId,
                    s.serviceName AS serviceName,
                    b.branchName AS branchName,
                    b.branchThumbnailUrl AS branchThumbnailUrl,
                    bs.branchServicePrice AS branchServicePrice
               FROM BranchServiceEntity bs
               INNER JOIN ShopServiceEntity s ON s.serviceId = bs.serviceId
               INNER JOIN BranchEntity b ON b.branchId = bs.branchId
               """)
     List<BranchServiceInfo> findAllServiceInfo();
}
