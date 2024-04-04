package com.realman.becore.dto.otp;

import lombok.Builder;

@Builder
public record OTP(
        Long otpId,
        Long accountId,
        String phoneAttemp,
        String passCode) {

}
