package com.realman.becore.repository.database.staff;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    Optional<StaffEntity> findByAccountId(Long accountId);
}
