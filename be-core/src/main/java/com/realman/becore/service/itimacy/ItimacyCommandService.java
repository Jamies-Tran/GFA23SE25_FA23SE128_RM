package com.realman.becore.service.itimacy;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.itimacy.ItimacyMapper;
import com.realman.becore.enums.EItimacyLevel;
import com.realman.becore.repository.database.itimacty_level.ItimacyLevelEntity;
import com.realman.becore.repository.database.itimacy.ItimacyEntity;
import com.realman.becore.repository.database.itimacy.ItimacyRepository;
import com.realman.becore.service.itimacy.level.ItimacyLevelQueryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItimacyCommandService {
    @NonNull
    private final ItimacyRepository itimacyRepository;

    @NonNull
    private final ItimacyLevelQueryService itimacyLevelQueryService;

    @NonNull
    private final ItimacyMapper itimacyMapper;

    public void save(Long customerId) {
        ItimacyLevelEntity itimacyLevelEntity = itimacyLevelQueryService.findByItimacy(EItimacyLevel.UNRANK);
        ItimacyEntity itimacyEntity = ItimacyEntity.builder()
                .currentPoint(0L)
                .customerId(customerId)
                .itimacyLevelId(itimacyLevelEntity.getItimactyLevelId())
                .build();
        itimacyRepository.save(itimacyEntity);
    }
}
