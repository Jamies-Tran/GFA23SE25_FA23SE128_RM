package com.realman.becore.controller.api.branch.models;

import java.util.List;

import lombok.Builder;

@Builder
public record BranchGroupByCityResponse(
    String city,
    Long branch,
    List<BranchResponse> branchList
) {
    
}
