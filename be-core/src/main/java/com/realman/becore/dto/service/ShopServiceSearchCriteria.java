package com.realman.becore.dto.service;

import java.util.Objects;
import lombok.Builder;

@Builder
public record ShopServiceSearchCriteria(String search, Long branchId) {

    public static ShopServiceSearchCriteria of(String search, Long branchId) {
        return ShopServiceSearchCriteria.builder()
                .search(search)
                .branchId(branchId)
                .build();
    }

    public ShopServiceSearchCriteria toLowerCase() {
        return ShopServiceSearchCriteria.builder()
                .search(search.toLowerCase())
                .branchId(branchId)
                .build();
    }

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }
}
