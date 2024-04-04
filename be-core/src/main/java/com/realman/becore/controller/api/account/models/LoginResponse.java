package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;

import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.ERole;

import lombok.Builder;

@Builder
public record LoginResponse(
        Long accountId,
        Long branchId,
        Long customerId,
        Long staffId,
        String phone,
        String jwtToken,
        ERole role,
        EAccountStatus status,
        LocalDateTime expTime) {

}
