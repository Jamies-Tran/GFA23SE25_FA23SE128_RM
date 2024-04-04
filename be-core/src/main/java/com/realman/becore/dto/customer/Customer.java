package com.realman.becore.dto.customer;

import lombok.Builder;

@Builder
public record Customer(
                Long customerId,
                Long accountId,
                Long serviceCount,
                Long profitProvided) {

}
