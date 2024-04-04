package com.realman.becore.controller.api.account.auth;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountModelMapper;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountId;
import com.realman.becore.service.account.AccountUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountAuthController implements AccountAuthAPI {

    @NonNull
    private final AccountUseCaseService accountUseCaseService;

    @NonNull
    private final AccountModelMapper accountModelMapper;

    @Override
    public ValueResponse<AccountResponse> findStaffAccount(Long accountId, Boolean isShowDistance, Double lat,
            Double lng) {
        Account dto = accountUseCaseService.findStaffAccount(new AccountId(accountId), isShowDistance, lat, lng);
        AccountResponse response = accountModelMapper.toModel(dto);
        return new ValueResponse<AccountResponse>(response);
    }

    @Override
    public ValueResponse<AccountResponse> findManagerAccount(Long accountId) {
        Account dto = accountUseCaseService.findManagerAccount(new AccountId(accountId));
        AccountResponse response = accountModelMapper.toModel(dto);
        return new ValueResponse<AccountResponse>(response);
    }

    @Override
    public ValueResponse<AccountResponse> findCustomerAccount(Long accountId) {
        Account dto = accountUseCaseService.findCustomerAccount(new AccountId(accountId));
        AccountResponse response = accountModelMapper.toModel(dto);
        return new ValueResponse<AccountResponse>(response);
    }

}
