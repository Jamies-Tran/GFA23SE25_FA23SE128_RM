package com.realman.becore.service.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.account.models.AccountRole;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountMapper;
import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.branch_manager.BranchManagerMapper;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.customer.CustomerMapper;
import com.realman.becore.dto.otp.OTP;
import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.receptionist.ReceptionistMapper;
import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.shop_owner.ShopOwnerMapper;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.dto.staff.StaffMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.enums.ERole;
import com.realman.becore.error_handlers.exceptions.AuthFailException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.account.AccountEntity;
import com.realman.becore.repository.database.account.AccountRepository;
import com.realman.becore.security.jwt.JwtConfiguration;
import com.realman.becore.service.branch_manager.BranchManagerUseCaseService;
import com.realman.becore.service.customer.CustomerUserCaseService;
import com.realman.becore.service.otp.OTPQueryService;
import com.realman.becore.service.receptionist.ReceptionistUseCaseService;
import com.realman.becore.service.shop_owner.ShopOwnerUseCaseService;
import com.realman.becore.service.staff.StaffUsecaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountCommandService {
        @NonNull
        private final AccountRepository accountRepository;
        @NonNull
        private final CustomerUserCaseService customerUserCaseService;
        @NonNull
        private final StaffUsecaseService staffUsecaseService;
        @NonNull
        private final BranchManagerUseCaseService branchManagerUseCaseService;
        @NonNull
        private final ShopOwnerUseCaseService shopOwnerUseCaseService;
        @NonNull
        private final ReceptionistUseCaseService receptionistUseCaseService;
        @NonNull
        private final AccountQueryService accountQueryService;
        @NonNull
        private final OTPQueryService otpQueryService;
        @NonNull
        private final JwtConfiguration jwtConfiguration;
        @NonNull
        private final PasswordEncoder passwordEncoder;
        @NonNull
        private final AccountMapper accountMapper;
        @NonNull
        private final CustomerMapper customerMapper;
        @NonNull
        private final StaffMapper staffMapper;
        @NonNull
        private final BranchManagerMapper branchManagerMapper;
        @NonNull
        private final ShopOwnerMapper shopOwnerMapper;
        @NonNull
        private final ReceptionistMapper receptionistMapper;

        public void save(Account account, OTP otp) {
                OTP foundOtp = otpQueryService.findByAccountId(otp.accountId());
                if (!passwordEncoder.matches(otp.passCode(), foundOtp.passCode())) {
                        throw new AuthFailException(EErrorMessage.ACCOUNT_NOT_VALID.name());
                }
                AccountEntity customerAccountEntity = accountMapper.toCustomerEntity(account,
                                ERole.CUSTOMER);
                AccountEntity savedCustomerAccountEntity = accountRepository
                                .save(customerAccountEntity);
                Customer customer = Customer.builder()
                                .accountId(savedCustomerAccountEntity.getAccountId())
                                .profitProvided(0L)
                                .serviceCount(0L)
                                .build();
                customerUserCaseService.save(customer);
        }

        public void save(Account account, AccountRole accountRole) {
                accountQueryService.verifyAccount(account);

                switch (accountRole.role()) {
                        case STAFF:
                                AccountEntity staffAccountEntity = accountMapper.toStaffEntity(account, ERole.STAFF);
                                AccountEntity savedStaffAccountEntity = accountRepository
                                                .save(staffAccountEntity);
                                Staff staff = Staff.builder()
                                                .accountId(savedStaffAccountEntity.getAccountId())
                                                .averageRating(0.0)
                                                .build();
                                staffUsecaseService.save(staff,
                                                accountRole.professional());
                                break;
                        case BRANCH_MANAGER:

                                AccountEntity managerAccountEntity = accountMapper.toManagerEntity(account,
                                                ERole.BRANCH_MANAGER);
                                AccountEntity savedManagerEntity = accountRepository.save(managerAccountEntity);
                                BranchManager branchManager = BranchManager.builder()
                                                .accountId(savedManagerEntity.getAccountId())
                                                .build();
                                branchManagerUseCaseService
                                                .save(branchManager);
                                break;
                        case SHOP_OWNER:

                                AccountEntity shopOwnerAccountEntity = accountMapper.toShopOwnerEntity(account,
                                                ERole.SHOP_OWNER);
                                AccountEntity savedShopOwnerAccountEntity = accountRepository
                                                .save(shopOwnerAccountEntity);
                                ShopOwner shopOwner = ShopOwner.builder()
                                                .accountId(savedShopOwnerAccountEntity.getAccountId())
                                                .build();
                                shopOwnerUseCaseService
                                                .save(shopOwner);
                                break;
                        case RECEPTIONIST:

                                AccountEntity receptAccountEntity = accountMapper.toReceptEntity(account,
                                                ERole.RECEPTIONIST);
                                AccountEntity savedReceptAccount = accountRepository.save(receptAccountEntity);
                                Receptionist receptionist = Receptionist.builder()
                                                .accountId(savedReceptAccount.getAccountId()).build();
                                receptionistUseCaseService
                                                .save(receptionist);
                                break;
                        default:
                                break;
                }
        }

        public void update(AccountId accountId, Account account) {
                AccountEntity accountEntity = accountRepository.findById(accountId.value())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                EErrorMessage.ACCOUNT_NOT_FOUND.name()));
                accountMapper.updateEntity(accountEntity, account);
                accountRepository.save(accountEntity);
        }

}
