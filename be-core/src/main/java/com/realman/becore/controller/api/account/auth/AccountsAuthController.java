package com.realman.becore.controller.api.account.auth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountModelMapper;
import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.account.AccountSearchCriteria;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.service.account.AccountUseCaseService;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountsAuthController implements AccountsAuthAPI {
        @NonNull
        private final AccountUseCaseService accountUseCaseService;

        @NonNull
        private final AccountModelMapper accountModelMapper;

        @Override
        public void saveStaff(@Valid AccountRequest account, EProfessional professional,
                        Long branchId) {
                Account dto = accountModelMapper.toDto(account.format(), professional);
                accountUseCaseService.saveStaff(dto, new BranchId(branchId), professional);
        }

        @Override
        public void saveManager(@Valid AccountRequest account, Long branchId) {
                Account dto = accountModelMapper
                                .toDto(account.format(), ERole.BRANCH_MANAGER);
                accountUseCaseService.save(dto, new BranchId(branchId));
        }

        @Override
        public PageImplResponse<AccountResponse> findAll(List<String> searches,
                        Long branchId,
                        ERole role,
                        Boolean isShowDistance,
                        Double lat,
                        Double lng,
                        @Min(1) Integer current,
                        Integer pageSize,
                        String sorter) {
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current);
                List<String> searchesCriteria = Objects.nonNull(searches) ? searches
                                : new ArrayList<>();
                AccountSearchCriteria criteria = AccountSearchCriteria.of(searchesCriteria, branchId, isShowDistance,
                                role, lat, lng);
                Page<Account> dtos = accountUseCaseService.findAll(criteria, pageRequestCustom);
                Page<AccountResponse> responses = dtos.map(accountModelMapper::toModel);
                return new PageImplResponse<>(
                                responses.getContent(),
                                responses.getTotalElements(),
                                responses.getTotalPages(),
                                responses.getSize(),
                                pageRequestCustom.current());
        }

        @Override
        public PageImplResponse<AccountResponse> findSuitableForBooking(Long branchId, LocalDateTime appointmentDate,
                        LocalDateTime startAppointment, LocalDateTime endAppointment, @Min(1) Integer current,
                        Integer pageSize,
                        String sorter) {
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current, sorter);
                Page<AccountResponse> responses = accountUseCaseService
                                .findSuitableForBooking(branchId, appointmentDate.toLocalDate(),
                                                startAppointment.toLocalTime(),
                                                endAppointment.toLocalTime(),
                                                pageRequestCustom)
                                .map(accountModelMapper::toModel);
                return new PageImplResponse<>(
                                responses.getContent(),
                                responses.getTotalElements(),
                                responses.getTotalPages(),
                                responses.getSize(),
                                pageRequestCustom.current());
        }

}
