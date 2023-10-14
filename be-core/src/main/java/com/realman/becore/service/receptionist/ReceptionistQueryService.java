package com.realman.becore.service.receptionist;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.receptionist.ReceptionistMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.receptionist.ReceptionistEntity;
import com.realman.becore.repository.database.receptionist.ReceptionistRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceptionistQueryService {
    @NonNull
    private ReceptionistRepository repReceptionistRepository;
    @NonNull
    private ReceptionistMapper receptionistMapper;

    public Receptionist findByAccountId(Long accountId) {
        ReceptionistEntity receptionistEntity = repReceptionistRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        EErrorMessage.RECEPTIONIST_NOT_FOUND.name()));
        return receptionistMapper.toDto(receptionistEntity);
    }
}
