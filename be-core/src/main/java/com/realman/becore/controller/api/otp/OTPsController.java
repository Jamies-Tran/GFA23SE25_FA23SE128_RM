package com.realman.becore.controller.api.otp;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.LoginRequest;
import com.realman.becore.controller.api.account.models.LoginResponse;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.controller.api.otp.models.OTPModelMapper;
import com.realman.becore.controller.api.otp.models.ValidAccount;
import com.realman.becore.service.otp.OTPUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OTPsController implements OTPsAPI {
    @NonNull
    private final OTPUseCaseService otpUseCaseService;

    @NonNull
    private final OTPModelMapper otpMapper;

    @Override
    public void save(AccountPhone accountPhone) {
        otpUseCaseService.save(accountPhone);

    }

    @Override
    public ValueResponse<LoginResponse> login(LoginRequest loginRequest) {
        LoginResponse login = otpUseCaseService.login(loginRequest);
        return new ValueResponse<>(login);
    }

    @Override
    public ValueResponse<ValidAccount> sendOtp(AccountPhone accountPhone) {
        ValidAccount accountExist = otpUseCaseService.accountRegister(accountPhone);
        return new ValueResponse<ValidAccount>(accountExist);
    }

}
