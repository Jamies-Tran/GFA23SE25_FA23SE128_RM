package com.realman.becore.repository.database.branch.display;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchDisplayRepository extends JpaRepository<BranchDisplayEntity, Long>{
    List<BranchDisplayEntity> findAllByBranchId(Long branchId);
}
