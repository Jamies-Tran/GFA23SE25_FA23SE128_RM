package com.realman.becore.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.branch.models.BranchId;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.enums.EProfessional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountUseCaseService {
    @NonNull
    private final AccountQueryService accountQueryService;

    @NonNull
    private final AccountCommandService accountCommandService;

    @Transactional
    public void saveStaff(Account account, BranchId branchId, EProfessional professional) {
        accountCommandService.saveStaff(account, branchId, professional);
    }

    @Transactional
    public void saveCustomer(Account account) {
        accountCommandService.saveCustomer(account);
    }

    @Transactional
    public void save(Account account) {
        accountCommandService.save(account);
    }

    @Transactional
    public void save(Account account, BranchId branchId) {
        accountCommandService.save(account, branchId);
    }

    public Account findStaffAccount(AccountId accountId) {
        return accountQueryService.findStaffAccount(accountId);
    }

    public Account findCustomerAccount(AccountId accountId) {
        return accountQueryService.findCustomerAccount(accountId);
    }

    public Account findManagerAccount(AccountId accountId) {
        return accountQueryService.findManagerAccount(accountId);
    }

    public Account findById(AccountId accountId) {
        return accountQueryService.findById(accountId);
    }

    public Account findByPhone(AccountPhone accountPhone) {
        return accountQueryService.findByPhone(accountPhone.value());
    }
}