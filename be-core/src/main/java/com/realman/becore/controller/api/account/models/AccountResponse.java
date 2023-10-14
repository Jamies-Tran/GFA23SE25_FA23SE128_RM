package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;

import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.enums.ERole;

import lombok.Builder;

@Builder
public record AccountResponse(
        String username,
        String phone,
        String address,
        LocalDateTime dob,
        Customer customer,
        Staff staff,
        Receptionist receptionist,
        BranchManager branchManager,
        ShopOwner shopOwner,
        ERole role) {

}
