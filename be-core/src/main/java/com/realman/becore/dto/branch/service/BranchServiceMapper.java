package com.realman.becore.dto.branch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.branch.BranchInfo;
import com.realman.becore.repository.database.branch.service.BranchServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchServiceMapper {
    BranchService toDto(BranchServiceInfo info);

    BranchServiceEntity toEntity(BranchServiceInfo info);

    @Mapping(source = "branchId", target = "branchId")
    BranchServiceEntity toEntity(BranchService dto, Long branchId);

    BranchService fromBranchInfo(BranchInfo branchInfo);
}
