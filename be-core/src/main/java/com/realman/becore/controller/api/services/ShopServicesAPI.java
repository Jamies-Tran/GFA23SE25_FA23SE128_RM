package com.realman.becore.controller.api.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.services.models.ShopServiceFieldResponse;
import com.realman.becore.controller.api.services.models.ShopServiceRequest;
import com.realman.becore.controller.api.services.models.ShopServiceResponse;
import com.realman.becore.util.response.ListResponse;
import com.realman.becore.util.response.PageImplResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Shop Service", description = "service management API")
@RequestMapping("/v1/services")
@PreAuthorize("hasAnyAuthority({'service:add', 'service:view'})")
public interface ShopServicesAPI {
    @PostMapping
    void save(@RequestBody @Valid ShopServiceRequest shopServiceRequest);

    @GetMapping
    PageImplResponse<ShopServiceResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "branchId") Long branchId,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "sorter", defaultValue = "serviceName") String sorter,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping("/field")
    ListResponse<ShopServiceFieldResponse> findAllServiceField(
            @RequestParam(required = false, defaultValue = "") Long branchId);
}
