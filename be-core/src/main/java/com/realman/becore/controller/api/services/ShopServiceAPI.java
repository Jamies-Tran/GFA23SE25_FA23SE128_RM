package com.realman.becore.controller.api.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.services.models.ShopServiceRequest;
import com.realman.becore.controller.api.services.models.ShopServiceResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Shop Service", description = "service management API")
@RequestMapping("/v1/service/{serviceId}")
public interface ShopServiceAPI {
    @GetMapping
    ValueResponse<ShopServiceResponse> findById(@PathVariable Long serviceId);

    @PutMapping
    void update(@PathVariable Long serviceId,@RequestBody @Valid ShopServiceRequest shopServiceRequest);
}
