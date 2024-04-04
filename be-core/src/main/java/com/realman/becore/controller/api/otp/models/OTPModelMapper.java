package com.realman.becore.controller.api.otp.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.dto.otp.OTP;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OTPModelMapper {
    OTPResponse toResponseModel(OTP dto);
}
