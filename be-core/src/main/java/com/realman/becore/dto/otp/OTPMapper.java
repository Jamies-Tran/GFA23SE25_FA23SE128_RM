package com.realman.becore.dto.otp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.otp.OTPEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OTPMapper {
    OTPEntity toEntity(String passCode, String phoneAttemp);

    OTP toDto(OTPEntity entity);
}
