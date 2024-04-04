package com.realman.becore.controller.api.branch;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.branch.models.BranchForAccountResponse;
import com.realman.becore.controller.api.branch.models.BranchGroupByCityResponse;
import com.realman.becore.controller.api.branch.models.BranchModelMapper;
import com.realman.becore.controller.api.branch.models.BranchRequest;
import com.realman.becore.controller.api.branch.models.BranchResponse;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchGroupByCity;
import com.realman.becore.dto.branch.BranchGroupByCitySearchCriteria;
import com.realman.becore.dto.branch.BranchSearchCriteria;
import com.realman.becore.service.branch.BranchUseCaseService;
import com.realman.becore.util.response.ListResponse;
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
                Branch dto = branchModelMapper.toDto(branch);
                branchUseCaseService.save(dto);
        }

        @Override
        public PageImplResponse<BranchResponse> findAll(List<LocalDateTime> timeRanges, String search,
                        Boolean isShowDistance, Double originLat, Double originLng, @Min(1) Integer current,
                        String sorter, Integer pageSize) {
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current, sorter);
                BranchSearchCriteria criteria = BranchSearchCriteria
                                .from(timeRanges, search, isShowDistance, originLat, originLng);
                Page<Branch> branches = branchUseCaseService
                                .findAll(criteria, pageRequestCustom);
                Page<BranchResponse> responses = branches.map(branch -> {
                        LocalDateTime open = LocalDateTime.now().toLocalDate().atTime(branch.open());
                        LocalDateTime close = LocalDateTime.now().toLocalDate().atTime(branch.close());
                        return branchModelMapper.toModel(branch, open, close);
                });
                return new PageImplResponse<>(
                                responses.getContent(),
                                responses.getTotalElements(),
                                responses.getTotalPages(),
                                pageSize,
                                current);
        }

        @Override
        public ListResponse<BranchGroupByCityResponse> findBranchByCity(String city, Boolean isShowDistance,
                        Double lat, Double lng, String sorter, Integer current, Integer pageSize) {
                BranchGroupByCitySearchCriteria searchCriteria = BranchGroupByCitySearchCriteria.builder()
                                .city(city).isShowDistance(isShowDistance).lat(lat).lng(lng).build();
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current, sorter);
                List<BranchGroupByCity> branchGroupingByCity = branchUseCaseService.findBranchByCity(searchCriteria,
                                pageRequestCustom);
                List<BranchGroupByCityResponse> responses = branchGroupingByCity.stream().map(branchGroupByCity -> {
                        List<BranchResponse> bResponses = branchGroupByCity.branchList().stream().map(branch -> {
                                LocalDateTime open = LocalDateTime.now().toLocalDate().atTime(branch.open());
                                LocalDateTime close = LocalDateTime.now().toLocalDate().atTime(branch.close());
                                return branchModelMapper.toModel(branch, open, close);
                        }).toList();
                        return BranchGroupByCityResponse.builder().city(branchGroupByCity.city())
                                        .branchList(bResponses).build();
                }).toList();
                return new ListResponse<>(responses);
        }

        @Override
        public ListResponse<BranchGroupByCityResponse> groupByCity() {
                List<BranchGroupByCity> groupByCityList = branchUseCaseService.groupByCity();
                List<BranchGroupByCityResponse> responses = groupByCityList.stream().map(branch -> {

                        return BranchGroupByCityResponse.builder().city(branch.city()).branch(branch.branch()).build();
                }).toList();
                return new ListResponse<>(responses);
        }

        @Override
        public ListResponse<BranchForAccountResponse> findBranchForAccount(String branchName) {
                List<BranchForAccountResponse> branchForAccountList = branchUseCaseService
                                .findBranchForAccountList(branchName).stream().map(branchModelMapper::toModel).toList();

                return new ListResponse<>(branchForAccountList);
        }

}
