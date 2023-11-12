package com.realman.becore.dto.branch.service;

import lombok.Builder;

@Builder
public record BranchService(
    Long serviceId,
    Long branchId,
    String branchName,
    String thumbnailUrl,
    Long price
) {
    
}
