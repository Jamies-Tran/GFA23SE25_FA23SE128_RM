package com.realman.becore.dto.account;

import java.time.LocalDateTime;

import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.EGender;
import com.realman.becore.dto.enums.EItimacyLevel;
import com.realman.becore.dto.enums.EProfessional;
import com.realman.becore.dto.enums.ERole;

public interface AccountInfo {
    void setAccountId(Long accountId);

    void setBranchId(Long branchId);

    void setStaffId(Long staffId);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setThumbnailUrl(String thumbnailUrl);

    void setGender(EGender gender);

    void setDob(LocalDateTime dob);

    void setPhone(String phone);

    void setAddress(String address);

    void setStatus(EAccountStatus status);

    void setRole(ERole role);

    void setBranchName(String branchName);

    void setBranchAddress(String branchAddress);

    void setProfessional(EProfessional professional);

    void setAverageRating(Double averageRating);

    void setItimacyLevel(EItimacyLevel itimacyLevel);

    Long getAccountId();

    Long getStaffId();

    Long getBranchId();

    String getFirstName();

    String getLastName();

    String getThumbnailUrl();

    EGender getGender();

    LocalDateTime getDob();

    String getPhone();

    String getAddress();

    EAccountStatus getStatus();

    ERole getRole();

    String getBranchName();

    String getBranchAddress();

    EItimacyLevel getItimacyLevel();

    EProfessional getProfessional();

    Double getAverageRating();
}
