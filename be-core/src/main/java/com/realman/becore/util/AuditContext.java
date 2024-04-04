package com.realman.becore.util;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.realman.becore.dto.account.Account;
import com.realman.becore.service.account.AccountQueryService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditContext {

    @NonNull
    private final AccountQueryService accountQueryService;

    public String auditor() {
        String phone = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Account account = accountQueryService.findByPhone(phone);
        return account.lastName();
    }

    public LocalDateTime auditTime() {
        return LocalDateTime.now();
    }
}
