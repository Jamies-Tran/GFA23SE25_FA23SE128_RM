package com.realman.becore.controller.api.branch.models;

public record BranchServiceResponse(
    Long serviceId,
    Long branchId,
    String branchName,
    String thumbnailUrl,
    Long price
) {
    
}
