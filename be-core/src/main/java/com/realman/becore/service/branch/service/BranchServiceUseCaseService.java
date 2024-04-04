package com.realman.becore.service.branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.branch.service.BranchService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceUseCaseService {
    @NonNull
    private final BranchServiceCommandService branchServiceCommandService;
    @NonNull
    private final BranchServiceQueryService branchServiceQueryService;

    @Transactional
    public void save(Long branchId, List<BranchService> branchService) {
        branchServiceCommandService.save(branchId, branchService);
    }

    @Transactional
    public void update(Long branchId, List<BranchService> branchServices) {
        branchServiceCommandService.update(branchId, branchServices);
    }

    public List<BranchService> findAllByServiceId(Long serviceId) {
        return branchServiceQueryService.findAllByServiceId(serviceId);
    }

    public List<BranchService> findAllByBranchId(Long branchId) {
        return branchServiceQueryService.findAllByBranchId(branchId);
    }

    public List<BranchService> findAll() {
        return branchServiceQueryService.findAll();
    }
}
