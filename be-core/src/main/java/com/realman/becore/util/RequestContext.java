package com.realman.becore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.realman.becore.controller.api.otp.models.AccountPhone;
import com.realman.becore.dto.account.Account;
import com.realman.becore.error_handlers.exceptions.ResourceInvalidException;
import com.realman.becore.security.jwt.JwtConfiguration;
import com.realman.becore.service.account.AccountUseCaseService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RequestContext {
    @NonNull
    private final JwtConfiguration jwtConfiguration;
    @Autowired
    @Lazy
    private AccountUseCaseService accountUseCaseService;

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private Account getUserFromRequest() {
        String accessToken = getRequest().getHeader("Authorization").substring(7);
        if (Objects.nonNull(accessToken)) {
            String userPhone = jwtConfiguration.getClaimsFromJwt(accessToken).getSubject();
            Account account = accountUseCaseService.findByPhone(new AccountPhone(userPhone));
            return account;
        }
        return null;
    }

    public Long getAccountId() {
        return getUserFromRequest().accountId();
    }

    public String getAccountPhone() {
        return getUserFromRequest().phone();
    }

    public Long getStaffId() {
        if (Objects.isNull(getUserFromRequest().staff())) {
            throw new ResourceInvalidException("Yêu cầu không hợp lệ");
        }
        return getUserFromRequest().staff().staffId();
    }

    public Long getBranchId() {
        if (Objects.isNull(getUserFromRequest().branch())) {
            throw new ResourceInvalidException("Yêu cầu không hợp lệ");
        }
        return getUserFromRequest().branch().branchId();
    }

    public Long getCustomerId() {
        if (Objects.isNull(getUserFromRequest().customer())) {
            throw new ResourceInvalidException("Yêu cầu không hợp lệ");
        }
        return getUserFromRequest().customer().customerId();
    }
}
