package com.realman.becore.service.customer;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.customer.Customer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUseCaseService {
    @NonNull
    private final CustomerCommandService customerCommandService;
    @NonNull
    private final CustomerQueryService customerQueryService;

    public Customer findByAccountId(Long accountId) {
        return customerQueryService.findByAccountId(accountId);
    }
}
