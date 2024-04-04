package com.realman.becore.controller.api.branch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record BranchServiceRequest(
                Long serviceId,
                @JsonProperty("price") Long branchServicePrice) {

}
