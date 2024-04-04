package com.realman.becore.controller.api.account;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountModelMapper;
import com.realman.becore.service.account.AccountUseCaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    @NonNull
    final AccountUseCaseService accountUseCaseService;
    @NonNull
    final AccountModelMapper accountModelMapper;

    
}
