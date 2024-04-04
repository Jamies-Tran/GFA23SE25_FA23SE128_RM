package com.realman.becore.dto.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {
        STAFF(Set.of(EPermission.ACCOUNT_VIEW.getPermission(), EPermission.BRANCH_VIEW.getPermission(),
                        EPermission.SCHEDULE_ADD.getPermission(), EPermission.SCHEDULE_VIEW.getPermission(),
                        EPermission.SERVICE_VIEW.getPermission(), EPermission.CATEGORY_VIEW.getPermission(),
                        EPermission.BOOKING_VIEW.getPermission(), EPermission.BOOKING_UPDATE.getPermission())),
        RECEPTIONIST(Set.of(EPermission.ACCOUNT_VIEW.getPermission(), EPermission.BRANCH_VIEW.getPermission(),
                        EPermission.BOOKING_ADD.getPermission(), EPermission.BOOKING_UPDATE.getPermission(),
                        EPermission.SCHEDULE_ADD.getPermission(), EPermission.SCHEDULE_VIEW.getPermission(),
                        EPermission.SERVICE_VIEW.getPermission(), EPermission.CATEGORY_VIEW.getPermission())),
        BRANCH_MANAGER(Set.of(EPermission.ACCOUNT_VIEW.getPermission(), EPermission.SCHEDULE_UPDATE.getPermission(),
                        EPermission.SCHEDULE_VIEW.getPermission(), EPermission.BRANCH_VIEW.getPermission(),
                        EPermission.SERVICE_VIEW.getPermission(), EPermission.CATEGORY_VIEW.getPermission())),
        SHOP_OWNER(Set.of(EPermission.ACCOUNT_VIEW.getPermission(), EPermission.ACCOUNT_ADD.getPermission(),
                        EPermission.ACCOUNT_UPDATE.getPermission(), EPermission.BRANCH_VIEW.getPermission(),
                        EPermission.BRANCH_ADD.getPermission(), EPermission.BRANCH_UPDATE.getPermission(),
                        EPermission.SERVICE_ADD.getPermission(), EPermission.SERVICE_UPDATE.getPermission(),
                        EPermission.SERVICE_VIEW.getPermission(), EPermission.SCHEDULE_VIEW.getPermission(),
                        EPermission.CATEGORY_ADD.getPermission(), EPermission.CATEGORY_UPDATE.getPermission(),
                        EPermission.CATEGORY_VIEW.getPermission(), EPermission.BOOKING_VIEW.getPermission())),
        CUSTOMER(Set.of(EPermission.BOOKING_ADD.getPermission(), EPermission.BOOKING_UPDATE.getPermission(),
                        EPermission.BOOKING_CANCLE.getPermission(), EPermission.ACCOUNT_VIEW.getPermission(),
                        EPermission.ACCOUNT_UPDATE.getPermission(), EPermission.BRANCH_VIEW.getPermission(),
                        EPermission.SCHEDULE_VIEW.getPermission(), EPermission.SERVICE_VIEW.getPermission(),
                        EPermission.CATEGORY_VIEW.getPermission()));

        private Set<String> permissions;

        public List<SimpleGrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
                this.permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
                return authorities;
        }
}
