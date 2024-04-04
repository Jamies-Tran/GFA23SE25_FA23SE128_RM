package com.realman.becore.dto.service;

import com.realman.becore.dto.enums.EDurationTime;

public interface ShopServiceInfo {
    void setServiceId(Long serviceId);

    void setCategoryId(Long categoryId);

    void setCategoryName(String categoryName);

    void setServiceName(String serviceName);

    void setServiceDescription(String serviceDescription);

    void setDuration(Integer duration);

    void setBranchId(Long branchId);

    void setBranchName(String branchName);

    void setBranchServicePrice(Long branchServicePrice);

    void setServicePrice(Long servicePrice);

    void setDurationValue(Integer durationValue);

    void setDurationTime(EDurationTime durationTime);

    void setDurationText(String durationText);

    Long getServiceId();

    Long getCategoryId();

    String getCategoryName();

    String getServiceName();

    String getServiceDescription();

    Integer getDuration();

    Long getBranchId();

    String getBranchName();

    Long getBranchServicePrice();

    Long getServicePrice();

    Integer getDurationValue();

    EDurationTime getDurationTime();

    String getDurationText();
}
