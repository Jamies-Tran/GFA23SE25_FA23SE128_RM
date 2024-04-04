package com.realman.becore.repository.database.itimacy.level;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realman.becore.dto.enums.EItimacyLevel;

@Repository
public interface ItimacyLevelRepository extends JpaRepository<ItimacyLevelEntity, Long> {
    Optional<ItimacyLevelEntity> findByItimacyLevel(EItimacyLevel itimacyLevel);
}
