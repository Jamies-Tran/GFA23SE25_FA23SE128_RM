package com.realman.becore.service.timeslot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.timeslot.TimeSlot;
import com.realman.becore.dto.timeslot.TimeSlotInfo;
import com.realman.becore.dto.timeslot.TimeSlotMapper;
import com.realman.becore.repository.database.timeslot.TimeSlotRepository;
import com.realman.becore.util.RequestContext;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSlotQueryService {
    @NonNull
    private final TimeSlotRepository timeSlotRepository;
    @NonNull
    private final TimeSlotMapper timeSlotMapper;
    @NonNull
    private final RequestContext requestContext;

    public List<TimeSlot> findTimeSlots(LocalDate chosenDate, Long staffId) {
        List<TimeSlotInfo> timeSlotInfos = timeSlotRepository.findAllInfoById(chosenDate, staffId);
        Map<Long, List<TimeSlotInfo>> timeSlotMap = timeSlotInfos.stream()
                .collect(Collectors.groupingBy(TimeSlotInfo::getTimeSlotId));
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlotMap.keySet().forEach(timeSlotId -> {
            Optional<TimeSlotInfo> timeSlotOptional = timeSlotMap.get(timeSlotId).stream()
                    .filter(info -> info.getIsAvailable() == false).findAny();
            TimeSlotInfo timeSlotInfo = timeSlotOptional.orElse(
                    timeSlotMap.get(timeSlotId).stream().filter(info -> info.getIsAvailable() == true).findAny()
                            .orElse(null));
            timeSlots.add(timeSlotMapper.toDto(timeSlotInfo));
        });
        return timeSlots;
    }

}
