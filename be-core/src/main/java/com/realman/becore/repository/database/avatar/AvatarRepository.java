package com.realman.becore.repository.database.avatar;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarEntity, Long> {
    
    @Query("""
            SELECT a FROM AvatarEntity a WHERE a.staffId = :staffId
            """)
    Page<AvatarEntity> findAll(Long staffId, Pageable pageable);
}
