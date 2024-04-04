package com.realman.becore.dto.branch;

import lombok.Builder;

@Builder
public record BranchForAccount(
    Long branchId,
    String branchName,
    String address,
    Integer numberStaffs
) {
    
}
