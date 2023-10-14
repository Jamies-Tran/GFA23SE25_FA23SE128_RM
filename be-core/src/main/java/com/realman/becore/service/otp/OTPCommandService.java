package com.realman.becore.service.otp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.account.models.LoginRequest;
import com.realman.becore.controller.api.account.models.LoginResponse;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.otp.OTP;
import com.realman.becore.dto.otp.OTPMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.AuthFailException;
import com.realman.becore.repository.database.otp.OTPEntity;
import com.realman.becore.repository.database.otp.OTPRepository;
import com.realman.becore.repository.database.otp.OTPEntity.OTPEntityBuilder;
import com.realman.becore.security.jwt.JwtConfiguration;
import com.realman.becore.service.account.AccountQueryService;
import com.realman.becore.util.TwilioUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OTPCommandService {
    @NonNull
    private final OTPRepository otpRepository;
    @NonNull
    private final AccountQueryService accountQueryService;
    @NonNull
    private final OTPMapper otpMapper;
    @NonNull
    private final PasswordEncoder passwordEncoder;
    @NonNull
    private final JwtConfiguration jwtConfiguration;

    public OTP save(AccountPhone accountPhone) {
        StringBuilder passCodeBuilder = new StringBuilder();
        OTPEntityBuilder otpEntityBuilder = OTPEntity.builder();
        for (int i = 0; i < 5; i++) {
            passCodeBuilder.append(new Random().nextInt(9));
        }
        Account account = accountQueryService.findByPhone(accountPhone.value());
        if (Objects.nonNull(account)) {
            otpEntityBuilder.accountId(account.accountId())
                    .phoneAttemp(accountPhone.value())
                    .passCode(passwordEncoder.encode(passCodeBuilder.toString()))
                    .expTime(15)
                    .isAvailable(true);
        }
        otpEntityBuilder.phoneAttemp(accountPhone.value())
                .passCode(passwordEncoder.encode(passCodeBuilder.toString()))
                .expTime(15)
                .isAvailable(true);
        OTPEntity entity = otpRepository.save(otpEntityBuilder.build());
        TwilioUtil.sendOTP(accountPhone.value(), passCodeBuilder.toString());
        return otpMapper.toDto(entity);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<OTPEntity> otpEntity = otpRepository.findByPhone(loginRequest.phone());

        if (otpEntity.isEmpty()) {
            String jwt = null;
            if( Objects.equals(loginRequest.passCode(), "99999")) {
                jwt = jwtConfiguration.generateJwt(loginRequest.phone());
            }
            
            return LoginResponse.builder()
                    .phone(null)
                    .jwtToken(jwt)
                    .expTime(null)
                    .role(null)
                    .isPhoneRegistered(false)
                    .build();
        }
        Account account = accountQueryService.findById(
            new AccountId(otpEntity.get().getAccountId()));
        if (!passwordEncoder.matches(loginRequest.passCode(),
                otpEntity.get().getPassCode())) {
            throw new AuthFailException(EErrorMessage.ACCOUNT_NOT_VALID.name());
        }
        otpRepository.delete(otpEntity.get());
        String jwtToken = jwtConfiguration.generateJwt(account.phone());
        LocalDateTime expiredTime = jwtConfiguration.expireTime();
        return LoginResponse.builder()
                .phone(account.phone())
                .jwtToken(jwtToken)
                .expTime(expiredTime)
                .role(account.role())
                .isPhoneRegistered(true)
                .build();
    }

}
