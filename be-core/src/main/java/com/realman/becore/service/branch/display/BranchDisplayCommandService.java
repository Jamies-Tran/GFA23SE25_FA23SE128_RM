package com.realman.becore.service.branch.display;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.branch.display.BranchDisplay;
import com.realman.becore.dto.branch.display.BranchDisplayMapper;
import com.realman.becore.repository.database.branch.display.BranchDisplayEntity;
import com.realman.becore.repository.database.branch.display.BranchDisplayRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchDisplayCommandService {
        @NonNull
        private final BranchDisplayRepository branchDisplayRepository;
        @NonNull
        private final BranchDisplayMapper branchDisplayMapper;

        public void save(List<BranchDisplay> branchDisplays, Long branchId) {
                List<BranchDisplayEntity> entityList = branchDisplays.stream()
                                .map(branchDisplay -> branchDisplayMapper.toEntity(branchDisplay, branchId)).toList();
                branchDisplayRepository.saveAll(entityList);
        }

        public void update(Long branchId, List<BranchDisplay> branchDisplays) {
                List<BranchDisplayEntity> branchDisplayList = branchDisplayRepository.findAllByBranchId(branchId);
                if (Objects.nonNull(branchDisplayList)) {
                        branchDisplayRepository.deleteAll(branchDisplayList);
                }
                if (Objects.nonNull(branchDisplays)) {
                        List<BranchDisplayEntity> updatedBranchDisplayList = branchDisplays.stream()
                                        .map(branchDisplay -> branchDisplayMapper.toEntity(branchDisplay, branchId))
                                        .toList();
                        branchDisplayRepository.saveAll(updatedBranchDisplayList);
                }

        }

        public void delete(Long branchId) {
                List<BranchDisplayEntity> entityList = branchDisplayRepository
                                .findAllByBranchId(branchId);
                branchDisplayRepository.deleteAll(entityList);
        }
}
