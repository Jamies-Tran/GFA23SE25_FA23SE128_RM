package com.realman.becore.controller.api.account;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.account.models.AccountModelMapper;
import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.controller.api.account.models.AccountRole;
import com.realman.becore.dto.otp.OTP;
import com.realman.becore.enums.EProfessional;
import com.realman.becore.enums.ERole;
import com.realman.becore.service.account.AccountUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    @NonNull
    final AccountUseCaseService accountUseCaseService;
    @NonNull
    final AccountModelMapper accountModelMapper;

    @Override
    public void save(@Valid AccountRequest accountRequest, ERole role,
            EProfessional professional) {
        AccountRole accountRole = AccountRole.builder().role(role).professional(professional).build();
        accountUseCaseService.save(accountModelMapper.toDto(accountRequest.format()), accountRole);
    }

    @Override
    public ValueResponse<AccountResponse> findById(Long accountId) {
        AccountResponse accountResponse = accountModelMapper
                .toModel(accountUseCaseService.findById(new AccountId(accountId)));
        return new ValueResponse<>(accountResponse);
    }

    @Override
    public void update(Long accountId,@Valid AccountRequest accountRequest) {
        accountUseCaseService.update(new AccountId(accountId), accountModelMapper.toDto(accountRequest));
    }

    @Override
    public void save(@Valid AccountRequest accountRequest, Long otpId, String passCode) {
        OTP otp = OTP.builder()
                .otpId(otpId)
                .passCode(passCode)
                .build();

        accountUseCaseService.save(accountModelMapper.toDto(accountRequest.format()), otp);
    }
}
