package com.realman.becore.repository.database.shop_owner;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOwnerRepository extends JpaRepository<ShopOwnerEntity, Long> {
    Optional<ShopOwnerEntity> findByAccountId(Long accountId);
}
