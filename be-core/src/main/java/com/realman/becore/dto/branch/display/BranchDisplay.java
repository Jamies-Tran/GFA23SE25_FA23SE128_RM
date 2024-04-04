package com.realman.becore.dto.branch.display;

public record BranchDisplay(
        Long branchDisplayId,
        Long branchId,
        String url,
        String branchDisplayBase64Url) {

}
