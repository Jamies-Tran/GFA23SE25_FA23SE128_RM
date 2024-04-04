package com.realman.becore.dto.branch;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.branch.display.BranchDisplay;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.enums.EBranchStatus;

import lombok.Builder;

@Builder
public record Branch(
                Long branchId,
                Long shopOwnerId,
                String branchName,
                @JsonProperty("thumbnailUrl") String branchThumbnailUrl,
                String branchThumbnailBase64Url,
                @JsonProperty("phone") String branchHotline,
                @JsonProperty("address") String branchAddress,
                @JsonProperty("status") EBranchStatus branchStatus,
                Integer numberStaffs,
                LocalTime open,
                LocalTime close,
                List<BranchDisplay> branchDisplayList,
                List<BranchService> branchServiceList,
                List<Account> accountStaffList,
                Double lat,
                Double lng,
                Double distance,
                String distanceKilometer) {

}
