package com.realman.becore.service.branch_manager;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.branch_manager.BranchManagerMapper;
import com.realman.becore.repository.database.branch_manager.BranchManagerRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchManagerCommandService {
    @NonNull
    private final BranchManagerRepository branchManagerRepository;

    @NonNull
    private final BranchManagerMapper branchManagerMapper;

    public void save(BranchManager branchManager) {
        branchManagerRepository
                .save(branchManagerMapper.toEntity(BranchManager.builder().build()));
    }

}
