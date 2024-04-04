package com.realman.becore.controller.api.services.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.dto.enums.EDurationTime;

import jakarta.validation.constraints.NotNull;

public record ShopServiceRequest(
        @JsonProperty("name") String serviceName,
        Long categoryId,
        @JsonProperty("price") Long servicePrice,
        @JsonProperty(value = "description") String serviceDescription,
        List<ServiceDisplayRequest> serviceDisplayList,
        @NotNull Integer durationValue,
        @NotNull EDurationTime durationTime) {

}
