package com.realman.becore.service.branch_manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.branch_manager.BranchManager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchManagerUseCaseService {
    @NonNull
    private final BranchManagerCommandService branchManagerCommandService;
    @NonNull
    private final BranchManagerQueryService branchManagerQueryService;

    @Transactional
    public void save(BranchManager branchManager) {
        branchManagerCommandService.save(branchManager);
    }

    public BranchManager findByAccountId(Long accountId) {
        return branchManagerQueryService.findByAccountId(accountId);
    }
}
