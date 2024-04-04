package com.realman.becore.service.account;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.otp.models.ValidAccount;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountId;
import com.realman.becore.dto.account.AccountInfo;
import com.realman.becore.dto.account.AccountMapper;
import com.realman.becore.dto.account.AccountSearchCriteria;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.account.AccountEntity;
import com.realman.becore.repository.database.account.AccountRepository;
import com.realman.becore.service.branch.BranchUseCaseService;
import com.realman.becore.service.customer.CustomerUseCaseService;
import com.realman.becore.service.staff.StaffUsecaseService;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountQueryService {
        @NonNull
        private final AccountRepository accountRepository;
        @NonNull
        private final AccountMapper accountMapper;
        @NonNull
        private final CustomerUseCaseService customerUserCaseService;
        @NonNull
        private final StaffUsecaseService staffUsercaseService;
        @NonNull
        private final BranchUseCaseService branchUseCaseService;

        public Account findByPhone(String phone) {
                AccountEntity entity = accountRepository
                                .findByPhone(phone)
                                .orElseThrow(ResourceNotFoundException::new);
                Account account = accountMapper.toDto(entity);
                if (entity.getRole().equals(ERole.STAFF) || entity.getRole().equals(ERole.RECEPTIONIST)) {
                        Staff staff = staffUsercaseService.findByAccountId(entity.getAccountId());
                        Branch branch = branchUseCaseService.findByAccountId(entity.getAccountId());
                        account = accountMapper.toDto(entity, staff, branch);
                } else if (entity.getRole().equals(ERole.CUSTOMER)) {
                        Customer customer = customerUserCaseService.findByAccountId(entity.getAccountId());
                        account = accountMapper.toDto(entity, customer);
                }
                return account;
        }

        public ValidAccount isAccountExist(String phone) {
                Optional<AccountEntity> entity = accountRepository.findByPhone(phone);
                Boolean isAccountExist = entity.isPresent();
                Boolean isAccountActivated = entity.isPresent()
                                ? entity.get().getStatus().equals(EAccountStatus.ACTIVATED)
                                : false;
                return new ValidAccount(isAccountExist, isAccountActivated);
        }

        public Account findStaffAccount(AccountId accountId, Boolean isShowDistance, Double lat, Double lng) {
                AccountInfo info = accountRepository.findStaffAccount(accountId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                Staff staff = staffUsercaseService.findByAccountId(accountId.value());
                Branch branch = branchUseCaseService
                                .findById(new BranchId(info.getBranchId()), isShowDistance, lat, lng);
                return accountMapper.fromInfo(info, staff, branch);
        }

        public Account findCustomerAccount(AccountId accountId) {
                AccountInfo info = accountRepository.findCustomerAccount(accountId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                return accountMapper.fromInfo(info);
        }

        public Account findManagerAccount(AccountId accountId) {
                AccountInfo info = accountRepository.findManagerAccount(accountId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                return accountMapper.fromInfo(info);
        }

        public Account findById(AccountId accountId) {
                AccountInfo info = accountRepository.findByAccountId(accountId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                return accountMapper.fromInfo(info);
        }

        public Page<Account> findAll(AccountSearchCriteria searchCriteria,
                        PageRequestCustom pageRequestCustom) {
                Page<AccountInfo> infoList = accountRepository.findAll(searchCriteria.toLowerCase(),
                                pageRequestCustom.pageRequest());
                return infoList.map(info -> {
                        Branch branch = branchUseCaseService.findById(new BranchId(info.getBranchId()),
                                        searchCriteria.isShowDistance(), searchCriteria.lat(), searchCriteria.lng());
                        Staff staff = staffUsercaseService.findByAccountId(info.getAccountId());
                        return accountMapper.fromInfo(info, staff, branch);
                });
        }

        public Page<Account> findSuitableForBooking(Long branchId, LocalDate appointmentDate,
                        LocalTime startAppointment, LocalTime endAppointment, PageRequestCustom pageRequestCustom) {
                Page<Account> accounts = accountRepository
                                .findSuitableForBooking(branchId, appointmentDate, startAppointment,
                                                endAppointment, pageRequestCustom.pageRequest())
                                .map(accountMapper::fromInfo);
                return accounts;
        }
}
