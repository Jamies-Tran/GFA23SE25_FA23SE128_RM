package com.realman.becore.repository.database.itimacy.level;

import com.realman.becore.dto.enums.EItimacyLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itimacy_level")
public class ItimacyLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itimacyLevelId;
    private Long requirePoint;
    private EItimacyLevel itimacyLevel;
    private Double percentDiscount;
}
