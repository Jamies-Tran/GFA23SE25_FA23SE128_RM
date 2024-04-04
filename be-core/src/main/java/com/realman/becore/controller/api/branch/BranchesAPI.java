package com.realman.becore.controller.api.branch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.branch.models.BranchForAccountResponse;
import com.realman.becore.controller.api.branch.models.BranchGroupByCityResponse;
import com.realman.becore.controller.api.branch.models.BranchRequest;
import com.realman.becore.controller.api.branch.models.BranchResponse;
import com.realman.becore.util.response.ListResponse;
import com.realman.becore.util.response.PageImplResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Branch", description = "manage branch API")
@RequestMapping("/v1/branches")
@PreAuthorize("hasAnyAuthority({'branch:add', 'branch:view'})")
public interface BranchesAPI {
        @PostMapping
        public void save(@RequestBody @Valid BranchRequest branch);

        @GetMapping
        PageImplResponse<BranchResponse> findAll(
                        @RequestParam(required = false, value = "timeRanges") @DateTimeFormat(pattern = "HH:mm:ss") List<LocalDateTime> timeRanges,
                        @RequestParam(required = false, value = "search", defaultValue = "") String search,
                        @RequestParam(required = false, value = "isShowDistance", defaultValue = "false") Boolean isShowDistance,
                        @RequestParam(required = false, value = "originLat", defaultValue = "0.0") Double originLat,
                        @RequestParam(required = false, value = "originLng", defaultValue = "0.0") Double originLng,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "sorter", defaultValue = "createdAt") String sorter,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);

        @GetMapping("/{city}")
        ListResponse<BranchGroupByCityResponse> findBranchByCity(
                        @PathVariable String city,
                        @RequestParam(required = false, value = "isShowDistance", defaultValue = "false") 
                        Boolean isShowDistance,
                        @RequestParam(required = false, value = "lat", defaultValue = "0.0") Double lat,
                        @RequestParam(required = false, value = "lng", defaultValue = "0.0") Double lng,
                        @RequestParam(required = false, value = "sorter", defaultValue = "branchName") String sorter,
                        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize);

        @GetMapping("/group-by-city")
        ListResponse<BranchGroupByCityResponse> groupByCity();
        
        @GetMapping("/branch-for-account/list")
        ListResponse<BranchForAccountResponse> findBranchForAccount(
                @RequestParam(required = false ,value = "branchName", defaultValue = "") String branchName);
}
