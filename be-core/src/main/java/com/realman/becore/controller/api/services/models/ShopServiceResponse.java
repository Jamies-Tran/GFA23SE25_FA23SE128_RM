package com.realman.becore.controller.api.services.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.enums.EDurationTime;

public record ShopServiceResponse(
                Long serviceId,
                Long categoryId,
                String categoryName,
                String branchName,
                @JsonProperty("name") String serviceName,
                @JsonProperty("description") String serviceDescription,
                @JsonProperty("price") Long servicePrice,
                Integer durationValue,
                EDurationTime durationTime,
                String durationText,
                List<ServiceDisplayResponse> serviceDisplayList,
                List<BranchService> branchServiceList) {

}
