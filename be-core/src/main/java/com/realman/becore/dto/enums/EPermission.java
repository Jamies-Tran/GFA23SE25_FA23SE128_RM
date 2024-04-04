package com.realman.becore.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EPermission {
    BOOKING_VIEW("booking:view"),
    BOOKING_ADD("booking:add"),
    BOOKING_UPDATE("booking:update"),
    BOOKING_CANCLE("booking:cancle"),
    SCHEDULE_VIEW("schedule:view"),
    SCHEDULE_ADD("schedule:add"),
    SCHEDULE_UPDATE("schedule:update"),
    ACCOUNT_VIEW("account:view"),
    ACCOUNT_ADD("account:add"),
    SERVICE_ADD("service:add"),
    SERVICE_VIEW("service:view"),
    SERVICE_UPDATE("service:update"),
    ACCOUNT_UPDATE("account:update"),
    BRANCH_VIEW("branch:view"),
    BRANCH_ADD("branch:add"),
    BRANCH_UPDATE("branch:update"),
    CATEGORY_ADD("category:add"),
    CATEGORY_VIEW("category:view"),
    CATEGORY_UPDATE("category:update");

    private String permission;
}
