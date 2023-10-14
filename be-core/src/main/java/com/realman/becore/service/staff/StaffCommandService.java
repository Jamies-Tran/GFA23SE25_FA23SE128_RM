package com.realman.becore.service.staff;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.staff.Staff;
import com.realman.becore.dto.staff.StaffMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.enums.EProfessional;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.staff.StaffRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffCommandService {
    @NonNull
    private final StaffRepository staffRepository;
    @NonNull
    private final StaffMapper staffMapper;

    public void save(Staff staff, EProfessional professional) {
        if (Objects.isNull(professional)) {
            throw new ResourceNotFoundException(EErrorMessage.PROFESSIONAL_NOT_FOUND.name());
        }
        staffRepository.save(staffMapper.toEntity(staff, professional));
    }
}
