package com.realman.becore.service.branch.display;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.branch.display.BranchDisplay;
import com.realman.becore.dto.branch.display.BranchDisplayMapper;
import com.realman.becore.repository.database.branch.display.BranchDisplayEntity;
import com.realman.becore.repository.database.branch.display.BranchDisplayRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchDisplayQueryService {

    @NonNull
    private final BranchDisplayRepository branchDisplayRepository;

    @NonNull
    private final BranchDisplayMapper branchDisplayMapper;

    public List<BranchDisplay> findAll(Long branchId) {
        List<BranchDisplayEntity> entityList = branchDisplayRepository
                .findAllByBranchId(branchId);
        return entityList.stream().map(branchDisplayMapper::toDto).toList();

    }

    public List<BranchDisplay> findAll() {
        List<BranchDisplayEntity> entityList = branchDisplayRepository.findAll();
        return entityList.stream().map(branchDisplayMapper::toDto).toList();
    }

}
