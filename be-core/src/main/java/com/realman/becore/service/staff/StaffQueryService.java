package com.realman.becore.service.staff;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.staff.Staff;
import com.realman.becore.dto.staff.StaffMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.staff.StaffEntity;
import com.realman.becore.repository.database.staff.StaffRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffQueryService {
    @NonNull
    private final StaffRepository staffRepository;
    @NonNull
    private final StaffMapper staffMapper;

    public Staff findByAccountId(Long accountId) {
        StaffEntity staffEntity = staffRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(EErrorMessage.STAFF_NOT_FOUND.name()));
        return staffMapper.toDto(staffEntity);
    }
}
