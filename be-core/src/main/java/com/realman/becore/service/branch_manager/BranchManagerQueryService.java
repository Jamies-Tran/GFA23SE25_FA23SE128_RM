package com.realman.becore.service.branch_manager;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.branch_manager.BranchManagerMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.branch_manager.BranchManagerEntity;
import com.realman.becore.repository.database.branch_manager.BranchManagerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchManagerQueryService {

    @NonNull
    private final BranchManagerRepository branchManagerRepository;

    @NonNull
    private final BranchManagerMapper branchManagerMapper;

    public BranchManager findByAccountId(Long accountId) {

        BranchManagerEntity branchManagerEntity = branchManagerRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(EErrorMessage.MANAGER_NOT_FOUND.name()));

        return branchManagerMapper.toDto(branchManagerEntity);
    }

}
