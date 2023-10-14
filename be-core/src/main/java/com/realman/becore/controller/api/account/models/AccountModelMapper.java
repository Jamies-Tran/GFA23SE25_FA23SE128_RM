package com.realman.becore.controller.api.account.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.account.Account;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountModelMapper {
     Account toDto(AccountRequest accountRequest);

    AccountResponse toModel(Account dto);
}
