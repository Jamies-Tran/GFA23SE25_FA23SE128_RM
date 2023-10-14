package com.realman.becore.repository.database.otp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {
    Optional<OTPEntity> findByAccountId(Long accountId);

    Optional<OTPEntity> findByPhoneAttemp(String phone);

    @Query("""
                SELECT o FROM AccountEntity a INNER JOIN OTPEntity o ON a.phone = o.phoneAttemp
                WHERE a.phone = :phone
            """)
    Optional<OTPEntity> findByPhone(String phone);
}
