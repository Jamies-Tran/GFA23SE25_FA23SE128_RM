package com.realman.becore.repository.database.service.display;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDisplayRepository extends JpaRepository<ServiceDisplayEntity, Long> {
    List<ServiceDisplayEntity> findAllByServiceId(Long serviceId);
}
