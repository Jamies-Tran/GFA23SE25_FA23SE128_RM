package com.realman.becore.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.repository.database.account.AccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountEntity toEntity(Account account);

    @Mapping(source = "branchId", target = "branchId")
    AccountEntity toEntity(Account account, Long branchId);

    Account toDto(AccountEntity entity);

    @Mapping(source = "entity.accountId", target = "accountId")
    Account toDto(AccountEntity entity, Staff staff);

    Account fromInfo(AccountInfo info);

    @Mapping(source = "info.accountId", target = "accountId")
    Account fromInfo(AccountInfo info, Staff staff);
}
