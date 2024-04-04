package com.realman.becore.controller.api.category.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realman.becore.controller.api.services.models.ShopServiceResponse;
import com.realman.becore.dto.enums.ECategoryType;

public record CategoryResponse(
                Long categoryId,
                @JsonProperty(value = "name") String categoryName,
                ECategoryType categoryType,
                List<ShopServiceResponse> serviceList) {
}
