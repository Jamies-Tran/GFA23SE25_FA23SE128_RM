package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;

import com.realman.becore.dto.enums.ERole;

import lombok.Builder;

@Builder
public record LoginResponse(
        Long accountId,
        String phone,
        String jwtToken,
        ERole role,
        LocalDateTime expTime) {

}