package com.realman.becore.service.itimacy.level;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.itimact_level.ItimacyLevelMapper;
import com.realman.becore.enums.EItimacyLevel;
import com.realman.becore.repository.database.itimacty_level.ItimacyLevelEntity;
import com.realman.becore.repository.database.itimacty_level.ItimacyLevelRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItimacyLevelCommandService {
        @NonNull
        private final ItimacyLevelRepository itimacyLevelRepository;

        @NonNull
        private final ItimacyLevelMapper itimacyLevelMapper;

        void saveAll() {
                List<ItimacyLevelEntity> itimacyLevelEntityList = itimacyLevelRepository.findAll();
                if (Objects.isNull(itimacyLevelEntityList) || itimacyLevelEntityList.isEmpty()) {
                        ItimacyLevelEntity unrank = ItimacyLevelEntity.builder()
                                        .itimacyLevel(EItimacyLevel.UNRANK)
                                        .percentDiscount(0.0)
                                        .requirePoint(0L)
                                        .build();
                        ItimacyLevelEntity bronze = ItimacyLevelEntity.builder()
                                        .itimacyLevel(EItimacyLevel.BRONZE)
                                        .percentDiscount(5.0)
                                        .requirePoint(1000L)
                                        .build();
                        ItimacyLevelEntity silver = ItimacyLevelEntity.builder()
                                        .itimacyLevel(EItimacyLevel.SILVER)
                                        .percentDiscount(10.0)
                                        .requirePoint(2000L)
                                        .build();
                        ItimacyLevelEntity gold = ItimacyLevelEntity.builder()
                                        .itimacyLevel(EItimacyLevel.GOLD)
                                        .percentDiscount(30.0)
                                        .requirePoint(6000L)
                                        .build();
                        ItimacyLevelEntity platinum = ItimacyLevelEntity.builder()
                                        .itimacyLevel(EItimacyLevel.PLATINUM)
                                        .percentDiscount(50.0)
                                        .requirePoint(10000L)
                                        .build();

                        itimacyLevelRepository.saveAll(List.of(unrank, bronze, silver, gold, platinum));
                }

        }
}
