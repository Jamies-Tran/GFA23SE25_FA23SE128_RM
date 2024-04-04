package com.realman.becore.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.controller.api.booking.models.ReceptBookingRequest;
import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchInfo;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.repository.database.account.AccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountEntity toEntity(Account account);

    @Mapping(source = "branchId", target = "branchId")
    AccountEntity toEntity(Account account, Long branchId);

    Account toDto(AccountEntity entity);

    @Mapping(source = "entity.accountId", target = "accountId")
    Account toDto(AccountEntity entity, Staff staff, Branch branch);

    @Mapping(source = "entity.accountId", target = "accountId")
    Account toDto(AccountEntity entity, Customer customer);

    Account fromInfo(AccountInfo info);

    @Mapping(source = "info.accountId", target = "accountId")
    @Mapping(source = "branch", target = "branch")
    Account fromInfo(AccountInfo info, Staff staff, Branch branch);

    @Mapping(source = "staff", target = "staff")
    @Mapping(source = "branchInfo.accountId", target = "accountId")
    Account fromBranchInfo(BranchInfo branchInfo, Staff staff);

    @Mapping(target = "role", expression = "java(com.realman.becore.dto.enums.ERole.CUSTOMER)")
    AccountEntity fromReceptBooking(ReceptBookingRequest receptBooking, EAccountStatus status);

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", source = "status")
    void update(@MappingTarget AccountEntity foundEntity, Account dto, EAccountStatus status);
}
