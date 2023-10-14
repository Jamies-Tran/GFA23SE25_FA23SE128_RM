package com.realman.becore.repository.database.receptionist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<ReceptionistEntity, Long> {

    Optional<ReceptionistEntity> findByAccountId(Long accountId);
}
