package com.realman.becore.service.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.customer.Customer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserCaseService {
    @NonNull
    private final CustomerCommandService customerCommandService;
    @NonNull
    private final CustomerQueryService customerQueryService;

    @Transactional
    public void save(Customer customer) {
        customerCommandService.save(customer);
    }

    public Customer findByAccountId(Long accountId) {
        return customerQueryService.findByAccountId(accountId);
    }
}
