package com.realman.becore.dto.booking;

import lombok.Builder;
import java.util.Objects;

@Builder
public record BookingSearchCriteria(Long branchId, Long customerId) {
    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasCustomerIdEmpty() {
        return Objects.isNull(customerId);
    }
}
