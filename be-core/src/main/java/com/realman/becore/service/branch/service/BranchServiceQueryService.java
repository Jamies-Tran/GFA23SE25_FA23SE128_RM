package com.realman.becore.service.branch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.branch.service.BranchServiceMapper;
import com.realman.becore.repository.database.branch.service.BranchServiceRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceQueryService {
    @NonNull
    private final BranchServiceRepository branchServiceRepository;
    @NonNull
    private final BranchServiceMapper branchServiceMapper;

    public List<BranchService> findAllByServiceId(Long serviceId) {
        List<BranchService> branchServiceList = branchServiceRepository.findAllByServiceId(serviceId)
                .stream().map(branchServiceMapper::toDto).toList();
        return branchServiceList;
    }

    public List<BranchService> findAllByBranchId(Long branchId) {
        List<BranchService> branchServiceList = branchServiceRepository.findAllByBranchId(branchId)
                .stream().map(branchServiceMapper::toDto).toList();
        return branchServiceList;
    }

    public List<BranchService> findAll() {
        List<BranchService> branchServiceList = branchServiceRepository.findAllServiceInfo().stream()
                .map(branchServiceMapper::toDto).toList();
        return branchServiceList;
    }
}
