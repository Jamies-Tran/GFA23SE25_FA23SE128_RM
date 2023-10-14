package com.realman.becore.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.account.models.AccountRole;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.otp.OTP;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountUseCaseService {
    @NonNull
    private final AccountQueryService accountQueryService;

    @NonNull
    private final AccountCommandService accountCommandService;

    /**
     * create account for customer
     * 
     * @param account
     * @param otp
     */
    @Transactional
    public void save(Account account, OTP otp) {
        accountCommandService.save(account, otp);
    }

    /**
     * create account for staff, receptionist, branch manager, shop owner
     * 
     * @param account
     * @param roleRequest
     */
    @Transactional
    public void save(Account account, AccountRole roleRequest) {
        accountCommandService.save(account, roleRequest);
    }

    @Transactional
    public void update(AccountId accountId, Account account) {
        accountCommandService.update(accountId, account);
    }

    public Account findByPhone(String phone) {
        return accountQueryService.findByPhone(phone);
    }

    public Account findById(AccountId accountId) {
        return accountQueryService.findById(accountId);
    }
}
