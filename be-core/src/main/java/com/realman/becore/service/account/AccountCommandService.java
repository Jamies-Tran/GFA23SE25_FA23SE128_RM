package com.realman.becore.service.account;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountMapper;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.error_handlers.exceptions.ResourceDuplicateException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.account.AccountEntity;
import com.realman.becore.repository.database.account.AccountRepository;
import com.realman.becore.service.customer.CustomerCommandService;
import com.realman.becore.service.otp.OTPCommandService;
import com.realman.becore.service.staff.StaffCommandService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountCommandService {
        @NonNull
        private final AccountRepository accountRepository;
        @NonNull
        private final CustomerCommandService customerCommandService;
        @NonNull
        private final StaffCommandService staffCommandService;
        @NonNull
        private final OTPCommandService otpCommandService;
        @NonNull
        private final AccountMapper accountMapper;

        public void saveStaff(Account account, BranchId branchId,
                        EProfessional professional) {
                Optional<AccountEntity> accountExisted = accountRepository
                                .findByPhone(account.phone());
                if (accountExisted.isPresent()) {
                        throw new ResourceDuplicateException();
                }
                AccountEntity entity = accountMapper.toEntity(account, branchId.value());
                AccountEntity savedEntity = accountRepository.save(entity);
                staffCommandService.save(
                                savedEntity.getAccountId(),
                                Objects.nonNull(professional) ? professional : EProfessional.STYLIST);
        }

        public void saveCustomer(Account account) {
                Optional<AccountEntity> accountExisted = accountRepository
                                .findByPhone(account.phone());
                if (accountExisted.isPresent()) {
                        throw new ResourceDuplicateException();
                }
                AccountEntity entity = accountMapper.toEntity(account);
                AccountEntity savedEntity = accountRepository.save(entity);
                customerCommandService.save(savedEntity.getAccountId());
                otpCommandService.save(new AccountPhone(account.phone()));
        }

        public void save(Account account, BranchId branchId) {
                Optional<AccountEntity> accountExisted = accountRepository
                                .findByPhone(account.phone());
                if (accountExisted.isPresent()) {
                        throw new ResourceDuplicateException();
                }
                AccountEntity entity = accountMapper.toEntity(account, branchId.value());
                accountRepository.save(entity);
        }

        public void save(Account account) {
                Optional<AccountEntity> accountExisted = accountRepository
                                .findByPhone(account.phone());
                if (accountExisted.isPresent()) {
                        throw new ResourceDuplicateException();
                }
                AccountEntity entity = accountMapper.toEntity(account);
                accountRepository.save(entity);
        }

        public Customer saveFromReceptBooking(ReceptBookingRequest receptBookingRequest) {
                if (accountRepository.findByPhone(receptBookingRequest.phone()).isPresent()) {
                        throw new ResourceDuplicateException();
                }
                AccountEntity account = accountMapper.fromReceptBooking(receptBookingRequest, EAccountStatus.PENDING);
                AccountEntity savedAccount = accountRepository.save(account);
                Customer customer = customerCommandService.save2(savedAccount.getAccountId());
                return customer;
        }

        public void update(String phone, Account account) {
                AccountEntity foundAccount = accountRepository.findByPhone(phone)
                                .orElseThrow(ResourceNotFoundException::new);
                accountMapper.update(foundAccount, account, EAccountStatus.ACTIVATED);
                accountRepository.save(foundAccount);
        }
}
