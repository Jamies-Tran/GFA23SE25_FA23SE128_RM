package com.realman.becore.controller.api.branch.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.custom_constrain.phone.Phone;
import com.realman.becore.custom_constrain.text.NormalText;
import com.realman.becore.dto.enums.EBranchStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record BranchRequest(
                Long shopOwnerId,
                @NormalText String branchName,
                @NormalText @JsonProperty("thumbnailUrl") String branchThumbnailUrl,
                String branchThumbnailBase64Url,
                @Phone @JsonProperty("phone") String branchHotline,
                @NormalText @JsonProperty("address") String branchAddress,
                @Enumerated(EnumType.STRING) @JsonProperty("status") EBranchStatus branchStatus,
                Integer numberStaffs,
                LocalDateTime open,
                LocalDateTime close,
                List<BranchDisplayRequest> branchDisplayList,
                List<BranchServiceRequest> branchServiceList) {

}
