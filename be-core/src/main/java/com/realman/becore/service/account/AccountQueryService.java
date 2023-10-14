package com.realman.becore.service.account;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountMapper;
import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceDuplicateException;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.account.AccountEntity;
import com.realman.becore.repository.database.account.AccountRepository;
import com.realman.becore.service.branch_manager.BranchManagerUseCaseService;
import com.realman.becore.service.customer.CustomerUserCaseService;
import com.realman.becore.service.receptionist.ReceptionistUseCaseService;
import com.realman.becore.service.shop_owner.ShopOwnerUseCaseService;
import com.realman.becore.service.staff.StaffUsecaseService;

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
        private final CustomerUserCaseService customerUserCaseService;
        @NonNull
        private final StaffUsecaseService staffUsercaseService;
        @NonNull
        private final ReceptionistUseCaseService receptionistUseCaseService;
        @NonNull
        private final BranchManagerUseCaseService branchManagerUserCaseService;
        @NonNull
        private final ShopOwnerUseCaseService shopOwnerUserCaseService;

        public Account findByPhone(String phone) {
                Optional<AccountEntity> accountEntity = accountRepository.findByPhone(phone);
                if (accountEntity.isEmpty()) {
                        return null;
                }
                return accountMapper.toDto(accountEntity.get());
        }

        public void verifyAccount(Account account) {
                if (accountRepository.findByPhone(account.phone()).isPresent()) {
                        throw new ResourceDuplicateException(EErrorMessage.PHONE_DUPLICATED.name());
                }
        }

        public Account findById(AccountId accountId) {
                AccountEntity accountEntity = accountRepository.findById(accountId.value())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                EErrorMessage.ACCOUNT_NOT_FOUND.name()));
                switch (accountEntity.getRole()) {
                        case CUSTOMER:
                                Customer customer = customerUserCaseService.findByAccountId(accountId.value());
                                return accountMapper.toCustomerDto(accountEntity, customer);
                        case STAFF:
                                Staff staff = staffUsercaseService.findByAccount(accountId.value());
                                return accountMapper.toStaffDto(accountEntity, staff);
                        case RECEPTIONIST:
                                Receptionist receptionist = receptionistUseCaseService
                                                .findByAccountId(accountId.value());
                                return accountMapper.toReceptDto(accountEntity, receptionist);
                        case BRANCH_MANAGER:
                                BranchManager branchManager = branchManagerUserCaseService
                                                .findByAccountId(accountId.value());
                                return accountMapper.toManagerDto(accountEntity, branchManager);
                        case SHOP_OWNER:
                                ShopOwner shopOwner = shopOwnerUserCaseService.findByAccountId(accountId.value());
                                return accountMapper.toShopOwnerDto(accountEntity, shopOwner);
                        default:
                                return accountMapper.toDto(accountEntity);
                }

        }

}
