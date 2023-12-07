package com.realman.becore.controller.api.account.auth;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.util.response.PageImplResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Account", description = "manage account by owner API")
@PreAuthorize("hasAnyRole({'ROLE_SHOP_OWNER', 'ROLE_BRANCH_MANAGER'})")
@RequestMapping("/v1/auth/accounts")
public interface AccountsAuthAPI {
        @PostMapping("/staff")
        void saveStaff(
                        @RequestBody @Valid AccountRequest account,
                        @RequestParam(required = false, value = "professional", defaultValue = "STYLIST") EProfessional professional,
                        @RequestParam(required = true, value = "branchId") Long branchId);

        @PostMapping("/manager")
        void saveManager(
                        @RequestBody @Valid AccountRequest account,
                        @RequestParam(required = true, value = "branchId") Long branchId);

        @GetMapping
        PageImplResponse<AccountResponse> findAll(
                        @RequestParam(required = false, value = "searches") List<String> searches,
                        @RequestParam(required = false) Long branchId,
                        @RequestParam(required = false, value = "role", defaultValue = "STAFF") ERole role,
                        @RequestParam(required = false, value = "iShowDistance", defaultValue = "false") Boolean isShowDistance,
                        @RequestParam(required = false, value = "lat", defaultValue = "0") Double lat,
                        @RequestParam(required = false, value = "lng", defaultValue = "0") Double lng,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize,
                        @RequestParam(required = false, value = "sorter", defaultValue = "createdAt") String sorter);
}
