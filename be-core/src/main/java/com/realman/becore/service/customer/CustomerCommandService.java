package com.realman.becore.service.customer;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.customer.CustomerMapper;
import com.realman.becore.dto.itimacy.ItimacyMapper;
import com.realman.becore.repository.database.customer.CustomerEntity;
import com.realman.becore.repository.database.customer.CustomerRepositoty;
import com.realman.becore.service.itimacy.ItimacyUsercaseService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCommandService {
    @NonNull
    private final CustomerRepositoty customerRepositoty;
    @NonNull
    private final CustomerMapper customerMapper;
    @NonNull
    private final ItimacyUsercaseService itimacyUsercaseService;
    @NonNull
    private final ItimacyMapper itimacyMapper;

    void save(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        CustomerEntity savedCustomerEntity = customerRepositoty.save(customerEntity);
        itimacyUsercaseService.save(savedCustomerEntity.getCustomerId());

    }

}
