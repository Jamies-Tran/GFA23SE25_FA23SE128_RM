package com.realman.becore.service.customer;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.customer.CustomerMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.customer.CustomerEntity;
import com.realman.becore.repository.database.customer.CustomerRepositoty;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {
    @NonNull
    private final CustomerRepositoty customerRepositoty;

    @NonNull
    private final CustomerMapper customerMapper;

    public Customer findByAccountId(Long accountId) {

        CustomerEntity customerEntity = customerRepositoty.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        EErrorMessage.CUSOMTER_NOT_FOUND.name()));
        return customerMapper.toDto(customerEntity);
    }
}
