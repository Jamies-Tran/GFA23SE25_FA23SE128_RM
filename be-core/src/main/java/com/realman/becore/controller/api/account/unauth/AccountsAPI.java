package com.realman.becore.controller.api.account.unauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.realman.becore.controller.api.account.models.AccountRequest;
import com.realman.becore.dto.account.Account;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Unauth Account", description = "management account API")
@RequestMapping("/v1/account")
public interface AccountsAPI {

    @PostMapping("/customer")
    void saveCustomer(@RequestBody @Valid AccountRequest account);

    @PostMapping("/owner")
    void saveOwner(@RequestBody @Valid AccountRequest account);

    @PutMapping("/{phone}")
    void update(@PathVariable String phone, @RequestBody AccountRequest accountRequest);

    @GetMapping("/{phone}")
    ValueResponse<Account> findByPhone(@PathVariable String phone);
}
