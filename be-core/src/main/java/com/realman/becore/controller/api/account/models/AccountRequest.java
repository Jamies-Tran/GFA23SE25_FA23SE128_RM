package com.realman.becore.controller.api.account.models;

import java.time.LocalDateTime;
import java.util.StringTokenizer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realman.becore.custom_constrain.phone.Phone;
import com.realman.becore.custom_constrain.text.NormalText;
import com.realman.becore.custom_constrain.username.FirstName;
import com.realman.becore.custom_constrain.username.LastName;
import com.realman.becore.dto.enums.EAccountStatus;
import com.realman.becore.dto.enums.EGender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record AccountRequest(
                @NormalText String thumbnailUrl,
                @FirstName String firstName,
                @LastName String lastName,
                @Phone String phone,
                String address,
                @Enumerated(EnumType.STRING) EGender gender,
                @JsonIgnore EAccountStatus status,
                LocalDateTime dob) {

        public AccountRequest format() {
                StringBuilder firstNameBuilder = new StringBuilder();
                StringTokenizer firstNameTokenizer = new StringTokenizer(firstName, " ");
                while (firstNameTokenizer.hasMoreTokens()) {
                        firstNameBuilder.append(formatName(firstNameTokenizer.nextToken())).append(" ");
                }
                AccountRequest account = AccountRequest.builder()
                                .thumbnailUrl(thumbnailUrl)
                                .firstName(firstNameBuilder.toString().strip())
                                .lastName(formatName(lastName))
                                .phone(phone)
                                .address(address)
                                .gender(gender)
                                .dob(dob)
                                .status(EAccountStatus.ACTIVATED)
                                .build();

                return account;
        }

        private String formatName(String name) {
                return String.valueOf(name.charAt(0)).toUpperCase()
                                .concat(name.substring(1));
        }
}
