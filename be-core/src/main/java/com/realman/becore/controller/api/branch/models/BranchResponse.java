package com.realman.becore.controller.api.branch.models;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.controller.api.account.models.AccountResponse;
import com.realman.becore.dto.enums.EBranchStatus;
import com.realman.becore.util.TimeZoneConfig;

import java.time.ZonedDateTime;

public record BranchResponse(
                Long branchId,
                Long shopOwnerId,
                String branchName,
                @JsonProperty(value = "thumbnailUrl") String branchThumbnailUrl,
                String branchThumbnailBase64Url,
                @JsonProperty(value = "phone") String branchHotline,
                @JsonProperty(value = "address") String branchAddress,
                @JsonProperty(value = "status") EBranchStatus branchStatus,
                Integer numberStaffs,
                LocalDateTime open,
                LocalDateTime close,
                List<BranchDisplayResponse> branchDisplayList,
                List<BranchServiceResponse> branchServiceList,
                List<AccountResponse> accountStaffList,
                String distanceKilometer) {
        public LocalDateTime open() {
                return ZonedDateTime.of(open, TimeZoneConfig.zoneId()).toLocalDateTime();
        }

        public LocalDateTime close() {
                return ZonedDateTime.of(close, TimeZoneConfig.zoneId()).toLocalDateTime();
        }
}
