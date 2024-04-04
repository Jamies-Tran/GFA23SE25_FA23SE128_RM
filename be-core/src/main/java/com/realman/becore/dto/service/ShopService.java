package com.realman.becore.dto.service;

import java.util.List;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.enums.EDurationTime;
import com.realman.becore.dto.enums.EServiceStatus;
import com.realman.becore.dto.service.display.ServiceDisplay;

import lombok.Builder;

@Builder
public record ShopService(
                Long serviceId,
                Long categoryId,
                String categoryName,
                String branchName,
                String serviceName,
                String serviceDescription,
                Integer durationValue,
                EDurationTime durationTime,
                String durationText,
                Long servicePrice,
                EServiceStatus status,
                List<ServiceDisplay> serviceDisplayList,
                List<BranchService> branchServiceList) {

}
