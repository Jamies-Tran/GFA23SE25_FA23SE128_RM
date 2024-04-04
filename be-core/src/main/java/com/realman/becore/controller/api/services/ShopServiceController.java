package com.realman.becore.controller.api.services;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.services.models.ServiceId;
import com.realman.becore.controller.api.services.models.ShopServiceModelMapper;
import com.realman.becore.controller.api.services.models.ShopServiceRequest;
import com.realman.becore.controller.api.services.models.ShopServiceResponse;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.service.services.ShopServiceUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopServiceController implements ShopServiceAPI {
    @NonNull
    private final ShopServiceUseCaseService shopServiceUseCaseService;
    @NonNull
    private final ShopServiceModelMapper shopServiceModelMapper;

    @Override
    public ValueResponse<ShopServiceResponse> findById(Long serviceId) {
        ShopService shopService = shopServiceUseCaseService.findByServiceId(new ServiceId(serviceId));
        ShopServiceResponse response = shopServiceModelMapper.toModel(shopService);
        return new ValueResponse<ShopServiceResponse>(response);
    }

    @Override
    public void update(Long serviceId, @Valid ShopServiceRequest shopServiceRequest) {
        ShopService shopService = shopServiceModelMapper.toDto(shopServiceRequest);
        shopServiceUseCaseService.update(serviceId, shopService);
    }
    
}
