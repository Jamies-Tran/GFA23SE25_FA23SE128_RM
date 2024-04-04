package com.realman.becore.service.services;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.enums.EServiceStatus;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.dto.service.ShopServiceMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.service.ShopServiceEntity;
import com.realman.becore.repository.database.service.ShopServiceRepository;
import com.realman.becore.service.services.display.ServiceDisplayCommandService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceCommandService {
    @NonNull
    private final ShopServiceRepository shopServiceRepository;
    @NonNull
    private final ServiceDisplayCommandService serviceDisplayCommandService;
    @NonNull
    private final ShopServiceMapper shopServiceMapper;

    public void save(ShopService shopService) {
        ShopServiceEntity entity = shopServiceMapper.toEntity(shopService, EServiceStatus.ACTIVATING);
        ShopServiceEntity shopServiceEntity = shopServiceRepository.save(entity);
        serviceDisplayCommandService.save(shopServiceEntity.getServiceId(), shopService.serviceDisplayList());
    }

    public void update(Long serviceId, ShopService shopService) {
        ShopServiceEntity foundService = shopServiceRepository.findById(serviceId)
                .orElseThrow(ResourceNotFoundException::new);
        serviceDisplayCommandService.update(serviceId, shopService.serviceDisplayList());
        shopServiceMapper.updateService(foundService, shopService);

    }
}
