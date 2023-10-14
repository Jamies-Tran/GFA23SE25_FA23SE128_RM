package com.realman.becore.controller.api.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.enums.EProfessional;
import com.realman.becore.enums.ERole;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Account")
@RequestMapping("/v1/account")
public interface AccountAPI {
    @PostMapping("/shop/{roleRequest}")
    void save(@RequestBody @Valid AccountRequest accountRequest,
            @RequestParam(required = true, value = "role", defaultValue = "") ERole role,
            @RequestParam(required = false, value = "professional", defaultValue = "") EProfessional professional);

    @PostMapping("/customer")
    void save(@RequestBody @Valid AccountRequest accountRequest,
            @RequestParam(required = true, value = "otpId") Long otpId,
            @RequestParam(required = true, value = "passCode") String passCode);

    @GetMapping
    ValueResponse<AccountResponse> findById(Long accountId);

    @PutMapping("/{accountId}")
    void update(@PathVariable Long accountId, @RequestBody @Valid AccountRequest accountRequest);
}
