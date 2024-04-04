package com.realman.becore.controller.api.branch.models;

import lombok.Builder;

@Builder
public record BranchForAccountResponse(
    Long branchId,
    String branchName,
    String address,
    Integer numberStaffs
) {
    
}
