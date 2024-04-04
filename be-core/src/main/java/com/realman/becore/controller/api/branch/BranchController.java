package com.realman.becore.controller.api.branch;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.branch.models.BranchForAccountResponse;
import com.realman.becore.controller.api.branch.models.BranchModelMapper;
import com.realman.becore.controller.api.branch.models.BranchRequest;
import com.realman.becore.controller.api.branch.models.BranchResponse;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchForAccount;
import com.realman.becore.dto.branch.BranchId;
import com.realman.becore.service.branch.BranchUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BranchController implements BranchAPI {

    @NonNull
    private final BranchUseCaseService branchUseCaseService;

    @NonNull
    private final BranchModelMapper branchModelMapper;

    @Override
    public void update(Long branchId, @Valid BranchRequest branch) {
        Branch dto = branchModelMapper.toDto(branch);
        branchUseCaseService.update(new BranchId(branchId), dto);
    }

    @Override
    public ValueResponse<BranchResponse> findById(Long branchId, Boolean isShowDistance, Double lat, Double lng) {
        Branch dto = branchUseCaseService.findById(new BranchId(branchId), isShowDistance, lat, lng);
        LocalDateTime open = LocalDateTime.now().toLocalDate().atTime(dto.open());
        LocalDateTime close = LocalDateTime.now().toLocalDate().atTime(dto.close());
        BranchResponse response = branchModelMapper.toModel(dto, open, close);
        return new ValueResponse<BranchResponse>(response);
    }

    @Override
    public ValueResponse<BranchForAccountResponse> findBranchForAccount(Long branchId) {
        BranchForAccount branchForAccount = branchUseCaseService.findBranchForAccount(branchId);
        BranchForAccountResponse response = branchModelMapper.toModel(branchForAccount);
        return new ValueResponse<>(response);
    }

}
