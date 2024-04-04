package com.realman.becore.service.account;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountId;
import com.realman.becore.dto.account.AccountSearchCriteria;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.util.response.PageRequestCustom;

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

    @Transactional
    public void update(String phone, Account account) {
        accountCommandService.update(phone, account);
    }

    public Account findStaffAccount(AccountId accountId, Boolean isShowDistance, Double lat, Double lng) {
        return accountQueryService.findStaffAccount(accountId, isShowDistance, lat, lng);
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

    public Page<Account> findAll(AccountSearchCriteria criteria,
            PageRequestCustom pageRequestCustom) {
        return accountQueryService.findAll(criteria, pageRequestCustom);
    }

    public Page<Account> findSuitableForBooking(Long branchId, LocalDate appointmentDate, LocalTime startAppointment,
            LocalTime endAppointment, PageRequestCustom pageRequestCustom) {
        return accountQueryService.findSuitableForBooking(branchId, appointmentDate, startAppointment, endAppointment,
                pageRequestCustom);
    }
}
