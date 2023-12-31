package com.realman.becore.security.user_detail_service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.otp.OTP;
import com.realman.becore.service.account.AccountUseCaseService;
import com.realman.becore.service.otp.OTPUserCaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @NonNull
    private final AccountUseCaseService accountUseCaseService;
    @NonNull
    private final OTPUserCaseService otpUserCaseService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Account account = accountUseCaseService.findAccountByPhone(phone);
        OTP otp = otpUserCaseService.findByAccountId(account.accountId());
        return User.builder().username(account.username()).password(otp.passCode())
                .authorities(account.role().getAuthorities()).build();
    }
}
