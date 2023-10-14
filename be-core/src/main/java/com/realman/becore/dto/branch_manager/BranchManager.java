package com.realman.becore.dto.branch_manager;

import lombok.Builder;

@Builder
public record BranchManager(
        Long branchManagerId,
        Long branchId,
        Long accountId) {

}
