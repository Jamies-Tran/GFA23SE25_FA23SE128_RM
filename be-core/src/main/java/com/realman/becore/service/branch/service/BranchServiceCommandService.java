package com.realman.becore.service.branch.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.branch.service.BranchServiceMapper;
import com.realman.becore.repository.database.branch.service.BranchServiceEntity;
import com.realman.becore.repository.database.branch.service.BranchServiceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceCommandService {
    @NonNull
    private final BranchServiceRepository branchServiceRepository;
    @NonNull
    private final BranchServiceMapper branchServiceMapper;

    public void save(Long branchId, List<BranchService> branchServices) {
        List<BranchServiceEntity> entityList = branchServices.stream()
                .map(branchService -> branchServiceMapper.toEntity(branchService, branchId)).toList();
        branchServiceRepository.saveAll(entityList);
    }

    public void update(Long branchId, List<BranchService> branchServices) {
        List<BranchServiceEntity> branchServiceList = branchServiceRepository.findAllByBranchId(branchId)
                .stream().map(branchServiceMapper::toEntity).toList();
        if(Objects.nonNull(branchServiceList)) {
            branchServiceRepository.deleteAll(branchServiceList);
        }
        if(Objects.nonNull(branchServices)) {
            List<BranchServiceEntity> updatedBranchServiceList = branchServices.stream()
                .map(branchService -> branchServiceMapper.toEntity(branchService, branchId)).toList();
            branchServiceRepository.saveAll(updatedBranchServiceList);
        }
        
    }
}
