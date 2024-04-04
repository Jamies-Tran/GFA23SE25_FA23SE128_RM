package com.realman.becore.service.staff;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.repository.database.staff.StaffEntity;
import com.realman.becore.repository.database.staff.StaffRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffCommandService {
    @NonNull
    private final StaffRepository staffRepository;

    public void save(Long accountId, EProfessional professional) {
        StaffEntity entity = StaffEntity.builder().accountId(accountId).professional(professional).build();
        staffRepository.save(entity);
    }
}
