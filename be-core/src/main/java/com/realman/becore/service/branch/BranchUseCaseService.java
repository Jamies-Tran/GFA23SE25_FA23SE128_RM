package com.realman.becore.service.branch;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchForAccount;
import com.realman.becore.dto.branch.BranchGroupByCity;
import com.realman.becore.dto.branch.BranchGroupByCitySearchCriteria;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.dto.branch.BranchSearchCriteria;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchUseCaseService {
    @NonNull
    private final BranchCommandService branchCommandService;

    @NonNull
    private final BranchQueryService branchQueryService;

    @Transactional
    public void save(Branch branch) {
        branchCommandService.save(branch);
    }

    @Transactional
    public void update(BranchId branchId, Branch branch) {
        branchCommandService.update(branchId, branch);
    }

    @Transactional
    public void delete(BranchId branchId) {
        branchCommandService.delete(branchId);
    }

    public Branch findById(BranchId branchId, Boolean isShowDistance, Double lat, Double lng) {
        return branchQueryService.findById(branchId, lat, lng, isShowDistance);
    }

    public Page<Branch> findAll(BranchSearchCriteria criteria, PageRequestCustom pageRequestCustom) {
        return branchQueryService.findAll(criteria, pageRequestCustom);
    }

    public List<BranchGroupByCity> groupByCity() {
        return branchQueryService.groupByCity();
    }

    public List<Branch> findByServiceId(Long serviceId) {
        return branchQueryService.findByServiceId(serviceId);
    }

    public List<BranchGroupByCity> findBranchByCity(BranchGroupByCitySearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return branchQueryService.findBranchByCity(searchCriteria, pageRequestCustom);
    }

    public List<BranchForAccount> findBranchForAccountList(String branchName) {
        return branchQueryService.findBranchForAccountList(branchName);
    }

    public BranchForAccount findBranchForAccount(Long branchId) {
        return branchQueryService.findBranchForAccount(branchId);
    }

    public Branch findByAccountId(Long accountId) {
        return branchQueryService.findByAccountId(accountId);
    }
}
