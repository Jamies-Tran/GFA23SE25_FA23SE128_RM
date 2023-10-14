package com.realman.becore.service.itimacy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItimacyUsercaseService {
    @NonNull
    private final ItimacyCommandService itimacyCommandService;

    @Transactional
    public void save(Long customerId) {
        itimacyCommandService.save(customerId);
    }
}
