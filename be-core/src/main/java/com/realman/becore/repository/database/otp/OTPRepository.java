package com.realman.becore.repository.database.otp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {
    Optional<OTPEntity> findByAccountId(Long accountId);

    Optional<OTPEntity> findByPhoneAttemp(String phone);

}
