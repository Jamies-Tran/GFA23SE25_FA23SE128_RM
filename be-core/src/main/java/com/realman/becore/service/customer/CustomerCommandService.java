package com.realman.becore.service.customer;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.customer.CustomerMapper;
import com.realman.becore.dto.itimacy.ItimacyMapper;
import com.realman.becore.repository.database.customer.CustomerEntity;
import com.realman.becore.repository.database.customer.CustomerRepositoty;
import com.realman.becore.service.itimacy.ItimacyCommandService;
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
    private final ItimacyCommandService itimacyCommandService;
    @NonNull
    private final ItimacyMapper itimacyMapper;

    public void save(Long accountId) {
        CustomerEntity entity = CustomerEntity.builder()
                .accountId(accountId)
                .serviceCount(0L)
                .profitProvided(0L).build();
        CustomerEntity savedCustomerEntity = customerRepositoty.save(entity);
        itimacyCommandService.save(savedCustomerEntity.getCustomerId());

    }

    public Customer save2(Long accountId) {
        CustomerEntity entity = CustomerEntity.builder()
                .accountId(accountId)
                .serviceCount(0L)
                .profitProvided(0L).build();
        CustomerEntity savedCustomerEntity = customerRepositoty.save(entity);
        itimacyCommandService.save(savedCustomerEntity.getCustomerId());
        return customerMapper.toDto(savedCustomerEntity);
    }
}
