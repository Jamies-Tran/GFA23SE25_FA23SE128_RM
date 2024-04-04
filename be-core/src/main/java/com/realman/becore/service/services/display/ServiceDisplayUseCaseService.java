package com.realman.becore.service.services.display;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.service.display.ServiceDisplay;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceDisplayUseCaseService {
    @NonNull
    private final ServiceDisplayCommandService serviceDisplayCommandService;
    @NonNull
    private final ServiceDisplayQueryService serviceDisplayQueryService;

    @Transactional
    public void save(Long serviceId, List<ServiceDisplay> serviceDisplays) {
        serviceDisplayCommandService.save(serviceId, serviceDisplays);
    }

    public List<ServiceDisplay> findByServiceId(Long serviceId) {
        return serviceDisplayQueryService.findByServieId(serviceId);
    }

    public List<ServiceDisplay> findAll() {
        return serviceDisplayQueryService.findAll();
    }

}
