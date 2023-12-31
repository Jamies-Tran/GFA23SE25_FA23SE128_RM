package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;

import com.realman.becore.enums.ERole;

import lombok.Builder;

@Builder
public record LoginResponse(
                String username,
                String jwtToken,
                ERole role,
                LocalDateTime expTime) {

}
