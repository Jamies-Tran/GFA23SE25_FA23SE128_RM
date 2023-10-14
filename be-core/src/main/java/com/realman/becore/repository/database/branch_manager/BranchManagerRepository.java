package com.realman.becore.repository.database.branch_manager;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchManagerRepository extends JpaRepository<BranchManagerEntity, Long> {
    Optional<BranchManagerEntity> findByAccountId(Long accountId);
}
