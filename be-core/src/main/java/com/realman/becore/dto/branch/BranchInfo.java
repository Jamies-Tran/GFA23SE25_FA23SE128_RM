package com.realman.becore.dto.branch;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.EBranchStatus;
import com.realman.becore.dto.enums.EGender;
import com.realman.becore.dto.enums.EProfessional;

public interface BranchInfo {
    // setter
    void setBranchId(Long brnachId);

    void setBranchName(String brnachName);

    void setBranchThumbnailUrl(String branchThumbnailUrl);

    void setBranchThumbnailBase64Url(String branchThumbnailBase64Url);

    void setBranchAddress(String branchAddress);

    void setBranchHotline(String branchHotline);

    void setNumberStaffs(Integer numberStaffs);

    void setCity(String city);

    void setLat(Double lat);

    void setLng(Double lng);

    void setBranchStatus(EBranchStatus branchStatus);

    void setOpen(LocalTime open);

    void setClose(LocalTime close);

    void setAccountId(Long accountId);

    void setThumbnailUrl(String thumbnailUrl);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setPhone(String phone);

    void setAddress(String address);

    void setDob(LocalDateTime dob);

    void setGender(EGender gender);

    void setStatus(EAccountStatus status);

    void setStaffId(Long staffId);

    void setAverageRating(Double averageRating);

    void setProfessional(EProfessional professional);

    // getter
    Long getBranchId();

    String getBranchName();

    String getBranchThumbnailUrl();

    String getBranchAddress();

    String getBranchHotline();

    Integer getNumberStaffs();

    String getCity();

    Double getLat();

    Double getLng();

    EBranchStatus getBranchStatus();

    LocalTime getOpen();

    LocalTime getClose();

    Long getAccountId();

    String getThumbnailUrl();

    String getFirstName();

    String getLastName();

    String getPhone();

    String getAddress();

    LocalDateTime getDob();

    EGender getGender();

    EAccountStatus getStatus();

    Long getStaffId();

    Double getAverageRating();

    EProfessional getProfessional();

    String getBranchThumbnailBase64Url();
}
