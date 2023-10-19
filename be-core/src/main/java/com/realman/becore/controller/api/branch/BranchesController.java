package com.realman.becore.controller.api.branch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.branch.models.BranchModelMapper;
import com.realman.becore.controller.api.branch.models.BranchRequest;
import com.realman.becore.controller.api.branch.models.BranchResponse;
import com.realman.becore.controller.api.branch.models.BranchSearchCriteria;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.service.branch.BranchUseCaseService;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BranchesController implements BranchesAPI {

        @NonNull
        private final BranchUseCaseService branchUseCaseService;

        @NonNull
        private final BranchModelMapper branchModelMapper;

        @Override
        public void save(@Valid BranchRequest branch) {
                Branch dto = branchModelMapper.toDto(branch,
                                branch.open().toLocalTime(),
                                branch.close().toLocalTime());
                branchUseCaseService.save(dto);
        }

        @Override
        public PageImplResponse<BranchResponse> findAll(List<LocalDateTime> timeRanges, List<String> searches,
                        @Min(1) Integer current, String sorter, Integer pageSize) {
                PageRequestCustom pageRequestCustom = PageRequestCustom
                                .of(pageSize, current, sorter);
                List<LocalTime> timeRangesCriteria = Objects.nonNull(timeRanges) ? timeRanges.stream()
                                .map(LocalDateTime::toLocalTime).toList() : new ArrayList<>();
                List<String> searchesCriteria = Objects.nonNull(searches) ? searches : new ArrayList<>();
                BranchSearchCriteria criteria = BranchSearchCriteria
                                .from(timeRangesCriteria, searchesCriteria);
                Page<Branch> branches = branchUseCaseService
                                .findAll(criteria, pageRequestCustom);
                Page<BranchResponse> responses = branches.map(branchModelMapper::toModel);
                return new PageImplResponse<>(
                                responses.getContent(),
                                responses.getTotalElements(),
                                pageSize,
                                current);
        }

}