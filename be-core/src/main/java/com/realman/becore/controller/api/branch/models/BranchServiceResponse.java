package com.realman.becore.controller.api.branch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchServiceResponse(
        Long serviceId,
        Long branchId,
        String serviceName,
        String branchName,
        String thumbnailUrl,
        @JsonProperty("price") Long branchServicePrice) {

}
