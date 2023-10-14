package com.realman.becore.service.receptionist;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.receptionist.ReceptionistMapper;
import com.realman.becore.repository.database.receptionist.ReceptionistRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceptionistCommandService {
    @NonNull
    private final ReceptionistRepository receptionistRepository;
    @NonNull
    private final ReceptionistMapper receptionistMapper;

    public void save(Receptionist receptionist) {
        receptionistRepository.save(receptionistMapper.toEntity(receptionist));
    }

}
