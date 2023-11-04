package com.realman.becore.repository.database.branch;

import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    @Query("""
            SELECT b FROM BranchEntity b
            WHERE (
                (:dateFrom IS NULL OR :dateTo IS NULL) OR (b.open BETWEEN :dateFrom AND :dateTo OR b.close BETWEEN :dateFrom AND :dateTo)
                    OR (:dateFrom BETWEEN b.open AND b.close OR :dateTo BETWEEN b.open AND b.close))
                AND (:#{#searches.isEmpty()} = TRUE) OR b.branchName IN (:searches) OR b.address IN (:searches)
                """)
    List<BranchEntity> findAll(
            LocalTime dateFrom,
            LocalTime dateTo,
            List<String> searches);
}
