package com.realman.becore.controller.api.branch.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.branch.Branch;
import com.realman.becore.dto.branch.BranchForAccount;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchModelMapper {

    @Mapping(target = "open", expression = "java(open(request.open()))")
    @Mapping(target = "close", expression = "java(close(request.close()))")
    Branch toDto(BranchRequest request);

    default LocalTime open(LocalDateTime open) {
        return open.plusHours(7).toLocalTime();
    }

    default LocalTime close(LocalDateTime close) {
        return close.plusHours(7).toLocalTime();
    }

    @Mapping(source = "open", target = "open")
    @Mapping(source = "close", target = "close")
    BranchResponse toModel(Branch dto, LocalDateTime open, LocalDateTime close);

    BranchForAccountResponse toModel(BranchForAccount dto);

}
