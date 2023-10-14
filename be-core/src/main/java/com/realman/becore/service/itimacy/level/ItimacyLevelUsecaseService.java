package com.realman.becore.service.itimacy.level;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItimacyLevelUsecaseService {

    @NonNull
    private final ItimacyLevelCommandService itimacyLevelCommandService;

    @Transactional
    public void saveAll() {
        itimacyLevelCommandService.saveAll();
    }
}
