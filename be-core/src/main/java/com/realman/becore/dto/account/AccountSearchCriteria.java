package com.realman.becore.dto.account;

import java.util.List;
import java.util.Objects;

import com.realman.becore.dto.enums.ERole;

import lombok.Builder;

@Builder
public record AccountSearchCriteria(
                List<String> searches,
                Long branchId,
                Boolean isShowDistance,
                ERole role,
                Double lat,
                Double lng) {
        public Boolean hasSearchEmpty() {
                return searches.isEmpty();
        }

        public Boolean hasBranchIdEmpty() {
                return Objects.isNull(branchId);
        }

        public static AccountSearchCriteria of(List<String> searches, Long branchId, Boolean isShowDistance,
                        ERole role, Double lat, Double lng) {
                return AccountSearchCriteria.builder()
                                .searches(searches)
                                .branchId(branchId)
                                .isShowDistance(isShowDistance)
                                .role(role)
                                .lat(lat)
                                .lng(lng)
                                .build();
        }

        public AccountSearchCriteria toLowerCase() {
                return AccountSearchCriteria.builder()
                                .branchId(branchId)
                                .searches(searches.stream().map(String::toLowerCase).toList())
                                .isShowDistance(isShowDistance)
                                .role(role)
                                .lat(lat)
                                .lng(lng)
                                .build();
        }
}
