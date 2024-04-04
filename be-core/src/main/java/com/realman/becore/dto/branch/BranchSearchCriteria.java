package com.realman.becore.dto.branch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record BranchSearchCriteria(
        LocalTime from,
        LocalTime to,
        Double originLat,
        Double originLng,
        Boolean isShowDistance,
        String search) {

    public static BranchSearchCriteria from(List<LocalDateTime> timeRanges, String search,
            Boolean isShowDistance, Double originLat, Double originLng) {
        timeRanges = Objects.nonNull(timeRanges) ? timeRanges : new ArrayList<>();
        BranchSearchCriteriaBuilder builder = BranchSearchCriteria.builder();
        if (!timeRanges.isEmpty()) {
            List<LocalTime> sortedTimeRanges = timeRanges.stream().sorted().map(LocalDateTime::toLocalTime).toList();
            builder.from(sortedTimeRanges.get(0)).to(sortedTimeRanges.get(1));
        }
        return builder.originLat(originLat).originLng(originLng).search(search)
                .isShowDistance(isShowDistance).build();
    }

}
