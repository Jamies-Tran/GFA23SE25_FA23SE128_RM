package com.realman.becore.repository.database.rating;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    @Query("""
            SELECT
                AVG(r.point)
            FROM RatingEntity r
            INNER JOIN StaffEntity s ON r.staffId = s.staffId
            WHERE s.staffId = :staffId
            GROUP BY r.point
            """)
    List<Double> averageRating(Long staffId);
}
