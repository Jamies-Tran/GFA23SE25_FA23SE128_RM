package com.realman.becore.service.shift;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.shift.Shift;
import com.realman.becore.dto.shift.ShiftMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.shift.ShiftEntity;
import com.realman.becore.repository.database.shift.ShiftRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftQueryService {
    @NonNull
    private final ShiftRepository shiftRepository;
    @NonNull
    private final ShiftMapper shiftMapper;

    public Shift findById(Long shiftId) {
        ShiftEntity shift = shiftRepository.findById(shiftId).orElseThrow(ResourceNotFoundException::new);
        return shiftMapper.toDto(shift);
    }
}
