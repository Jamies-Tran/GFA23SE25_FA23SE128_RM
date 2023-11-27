package com.realman.becore.dto.account;

import java.time.LocalDateTime;

import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.EGender;
import com.realman.becore.dto.enums.EItimacyLevel;
import com.realman.becore.dto.enums.ERole;
import com.realman.becore.dto.staff.Staff;

import lombok.Builder;

@Builder
public record Account(
                Long accountId,
                Long branchId,
                String thumbnailUrl,
                String firstName,
                String lastName,
                String phone,
                String address,
                LocalDateTime dob,
                EGender gender,
                EAccountStatus status,
                String branchName,
                String branchAddress,
                Staff staff,
                EItimacyLevel itimacyLevel,
                ERole role) {

}
