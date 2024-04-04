package com.realman.becore.dto.booking.service;

import java.util.Objects;
import com.realman.becore.dto.enums.EBookingServiceStatus;
import com.realman.becore.dto.enums.EBookingServiceType;

import lombok.Builder;

@Builder
public record BookingServiceSearchCriteria(String search, Long branchId, EBookingServiceType bookingServiceType,
        EBookingServiceStatus bookingServiceStatus) {

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }

    public Boolean hasTypeEmpty() {
        return Objects.isNull(bookingServiceType);
    }

    public Boolean hasStatusEmpty() {
        return Objects.isNull(bookingServiceStatus);
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public BookingServiceSearchCriteria toLowerCase() {
        return BookingServiceSearchCriteria.builder()
                .search(search.toLowerCase())
                .branchId(branchId)
                .bookingServiceStatus(bookingServiceStatus)
                .bookingServiceType(bookingServiceType)
                .build();
    }
}
