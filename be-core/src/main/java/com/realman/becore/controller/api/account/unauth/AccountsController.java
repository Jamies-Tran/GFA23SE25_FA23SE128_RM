package com.realman.becore.controller.api.account.unauth;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountModelMapper;
import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.service.account.AccountUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountsController implements AccountsAPI {

    @NonNull
    private final AccountUseCaseService accountUseCaseService;

    @NonNull
    private final AccountModelMapper accountModelMapper;

    @Override
    public void saveCustomer(@Valid AccountRequest account) {
        Account dto = accountModelMapper.toDto(account.format(), ERole.CUSTOMER);
        accountUseCaseService.saveCustomer(dto);
    }

    @Override
    public void saveOwner(@Valid AccountRequest account) {
        Account dto = accountModelMapper.toDto(account.format(), ERole.SHOP_OWNER);
        accountUseCaseService.save(dto);
    }

    @Override
    public void update(String phone, AccountRequest accountRequest) {
        Account account = accountModelMapper.toDto(accountRequest);
        accountUseCaseService.update(phone, account);
    }

    @Override
    public ValueResponse<Account> findByPhone(String phone) {
        Account account = accountUseCaseService.findByPhone(new AccountPhone(phone));
        return new ValueResponse<Account>(account);
    }
}
