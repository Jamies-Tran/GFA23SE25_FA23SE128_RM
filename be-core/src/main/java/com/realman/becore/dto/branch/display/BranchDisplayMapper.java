package com.realman.becore.dto.branch.display;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.branch.display.BranchDisplayEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchDisplayMapper {

    @Mapping(source = "branchId", target = "branchId")
    BranchDisplayEntity toEntity(BranchDisplay branchDisplay, Long branchId);

    BranchDisplay toDto(BranchDisplayEntity entity);
}
