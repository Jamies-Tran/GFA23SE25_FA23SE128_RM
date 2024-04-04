package com.realman.becore.repository.database.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoty extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByAccountId(Long accountId);

}
