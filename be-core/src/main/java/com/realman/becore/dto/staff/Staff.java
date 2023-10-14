package com.realman.becore.dto.staff;

import com.realman.becore.enums.EProfessional;

import lombok.Builder;

@Builder
public record Staff(
                Long staffId,
                Long accountId,
                Double averageRating,
                EProfessional professional) {

}
