package com.realman.becore.controller.api.otp;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.LoginRequest;
import com.realman.becore.controller.api.account.models.LoginResponse;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.controller.api.otp.models.OTPModelMapper;
import com.realman.becore.controller.api.otp.models.OTPResponse;
import com.realman.becore.service.otp.OTPUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OTPController implements OTPAPI {
    @NonNull
    private final OTPUseCaseService otpUserCaseService;

    @NonNull
    private final OTPModelMapper otpMapper;

    @Override
    public ValueResponse<OTPResponse> save(AccountPhone phone) {
        return new ValueResponse<OTPResponse>(otpMapper.toResponseModel(otpUserCaseService.save(phone)));

    }

    @Override
    public ValueResponse<LoginResponse> login(LoginRequest loginRequest) {
        return new ValueResponse<>(otpUserCaseService.login(loginRequest));
    }

}
