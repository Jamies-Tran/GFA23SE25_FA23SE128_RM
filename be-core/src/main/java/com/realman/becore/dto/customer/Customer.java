package com.realman.becore.dto.customer;

import lombok.Builder;

@Builder
public record Customer(
        Long accountId,
        Long customerId,
        Long serviceCount,
        Long profitProvided) {

}
