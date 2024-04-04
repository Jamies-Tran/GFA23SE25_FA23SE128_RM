package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.account.Account;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.error_handlers.exceptions.ResourceInvalidException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountModelMapper {

    @Mapping(expression = "java(getRole(professional))", target = "role")
    @Mapping(expression = "java(dob(model.dob()))", target = "dob")
    Account toDto(AccountRequest model, EProfessional professional);

    Account toDto(AccountRequest model, ERole role);

    Account toDto(AccountRequest model);

    AccountResponse toModel(Account dto);

    default ERole getRole(EProfessional professional) {
        if (!professional.equals(EProfessional.MASSEUR) && !professional.equals(EProfessional.STYLIST) && !professional
                .equals(EProfessional.RECEPTIONIST)) {
            throw new ResourceInvalidException();
        }
        if (professional.equals(EProfessional.MASSEUR) || professional.equals(EProfessional.STYLIST)) {
            return ERole.STAFF;
        }
        return ERole.RECEPTIONIST;
    }

    default LocalDateTime dob(LocalDateTime dob) {
        return dob.plusHours(7);
    }
}
