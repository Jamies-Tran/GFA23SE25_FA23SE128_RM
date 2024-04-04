package com.realman.becore.service.shift;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.enums.EShift;
import com.realman.becore.dto.shift.Shift;
import com.realman.becore.dto.shift.ShiftMapper;
import com.realman.becore.repository.database.shift.ShiftEntity;
import com.realman.becore.repository.database.shift.ShiftRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftCommandService {
    @NonNull
    private final ShiftRepository shiftRepository;
    @NonNull
    private final ShiftMapper shiftMapper;

    public void saveDefault() {
        List<Shift> shifts = List.of(
                Shift.builder().shift(EShift.MORNING).startShift(LocalTime.of(6, 0, 0))
                        .endShift(LocalTime.of(14, 0, 0)).build(),
                Shift.builder().shift(EShift.NIGHT).startShift(LocalTime.of(14, 30, 0))
                        .endShift(LocalTime.of(22, 30, 0)).build());
        List<ShiftEntity> foundShiftList = shiftRepository.findAll();
        if (Objects.isNull(foundShiftList) || foundShiftList.isEmpty()) {
            List<ShiftEntity> shiftList = shifts.stream().map(shiftMapper::toEntity).toList();
            shiftRepository.saveAll(shiftList);
        }
    }
}
