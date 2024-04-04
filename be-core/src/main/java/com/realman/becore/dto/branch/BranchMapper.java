package com.realman.becore.dto.branch;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.branch.display.BranchDisplay;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.repository.database.branch.BranchEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
        @Mapping(source = "lat", target = "lat")
        @Mapping(source = "lng", target = "lng")
        BranchEntity toEntity(Branch dto, String city, Double lat, Double lng);

        Branch toDto(BranchEntity entity, Double distance, List<BranchDisplay> branchDisplayList,
                        List<BranchService> branchServiceList);

        Branch toDto(BranchEntity entity, List<BranchDisplay> branchDisplayList, List<BranchService> branchServiceList,
                        List<Account> accountStaffList);

        Branch toDto(BranchEntity entity, List<BranchDisplay> branchDisplayList, List<BranchService> branchServiceList);

        Branch toDto(BranchEntity entity);

        @Mapping(source = "distanceKilometer", target = "distanceKilometer")
        Branch updateDto(Branch updateDto, String distanceKilometer);

        BranchGroupByCity toDto(BranchGroupByCityInfo info);

        BranchForAccount toDto(BranchInfo info);

        @Mapping(target = "branchId", ignore = true)
        @Mapping(target = "lat", ignore = true)
        @Mapping(target = "lng", ignore = true)
        void updateEntity(@MappingTarget BranchEntity entity, Branch dto);

}
