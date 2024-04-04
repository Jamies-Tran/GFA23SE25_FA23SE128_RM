package com.realman.becore.service.services.display;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.service.display.ServiceDisplay;
import com.realman.becore.dto.service.display.ServiceDisplayMapper;
import com.realman.becore.repository.database.service.display.ServiceDisplayRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceDisplayQueryService {
    @NonNull
    private final ServiceDisplayRepository serviceDisplayRepository;
    @NonNull
    private final ServiceDisplayMapper serviceDisplayMapper;

    public List<ServiceDisplay> findByServieId(Long serviceId) {
        List<ServiceDisplay> serviceDisplays = serviceDisplayRepository.findAllByServiceId(serviceId)
                .stream().map(serviceDisplayMapper::toDto).toList();
        return serviceDisplays;
    }

    public List<ServiceDisplay> findAll() {
        List<ServiceDisplay> serviceDisplays = serviceDisplayRepository.findAll()
                .stream().map(serviceDisplayMapper::toDto).toList();

        return serviceDisplays;
    }
}
