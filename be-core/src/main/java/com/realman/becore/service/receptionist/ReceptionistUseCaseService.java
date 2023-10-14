package com.realman.becore.service.receptionist;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.receptionist.Receptionist;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceptionistUseCaseService {
    @NonNull
    private final ReceptionistCommandService receptionistCommandService;
    @NonNull
    private final ReceptionistQueryService receptionistQueryService;

    @Transactional
    public void save(Receptionist receptionist) {
        receptionistCommandService.save(receptionist);
    }

    public Receptionist findByAccountId(Long accountId) {
        return receptionistQueryService.findByAccountId(accountId);
    }

}
