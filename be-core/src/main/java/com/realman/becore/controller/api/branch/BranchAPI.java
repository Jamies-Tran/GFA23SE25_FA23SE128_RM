package com.realman.becore.controller.api.branch;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.branch.models.BranchForAccountResponse;
import com.realman.becore.controller.api.branch.models.BranchRequest;
import com.realman.becore.controller.api.branch.models.BranchResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Branch", description = "manage branch API")
@RequestMapping("/v1/branch/{branchId}")
@PreAuthorize("hasAnyAuthority({'branch:update', 'branch:view'})")
public interface BranchAPI {

    @PutMapping
    void update(@PathVariable Long branchId, @RequestBody @Valid BranchRequest branch);

    @GetMapping
    ValueResponse<BranchResponse> findById(
            @PathVariable Long branchId,
            @RequestParam(required = false, value = "isShowDistance", defaultValue = "false") Boolean isShowDistance,
            @RequestParam(required = false, value = "lat", defaultValue = "0") Double lat,
            @RequestParam(required = false, value = "lng", defaultValue = "0") Double lng);

    @GetMapping("/branch-for-account")
    ValueResponse<BranchForAccountResponse> findBranchForAccount(@PathVariable Long branchId);
}
