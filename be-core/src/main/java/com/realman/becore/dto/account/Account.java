package com.realman.becore.dto.account;

import java.time.LocalDateTime;

import com.realman.becore.dto.branch_manager.BranchManager;
import com.realman.becore.dto.customer.Customer;
import com.realman.becore.dto.receptionist.Receptionist;
import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.staff.Staff;
import com.realman.becore.enums.EGender;
import com.realman.becore.enums.ERole;

public record Account(
                Long accountId,
                String firstName,
                String lastName,
                String phone,
                String address,
                LocalDateTime dob,
                EGender gender,
                Customer customer,
                Staff staff,
                Receptionist receptionist,
                BranchManager manager,
                ShopOwner shopOwner,
                ERole role) {

}
