package com.realman.becore.controller.api.otp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.account.models.LoginRequest;
import com.realman.becore.controller.api.account.models.LoginResponse;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.controller.api.otp.models.OTPResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/v1/otp")
@Tag(name = "OTP")
public interface OTPAPI {

    @PostMapping
    ValueResponse<OTPResponse> save(@RequestBody AccountPhone accountPhone);

    @PostMapping("/login")
    ValueResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest);
}
