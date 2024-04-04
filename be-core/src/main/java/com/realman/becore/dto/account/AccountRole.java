package com.realman.becore.dto.account;

import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.ERole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record AccountRole(
                @Enumerated(EnumType.STRING) ERole role,
                @Enumerated(EnumType.STRING) EProfessional professional) {
}
