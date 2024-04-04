package com.realman.becore.service.services.display;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.service.display.ServiceDisplay;
import com.realman.becore.dto.service.display.ServiceDisplayMapper;
import com.realman.becore.repository.database.service.display.ServiceDisplayEntity;
import com.realman.becore.repository.database.service.display.ServiceDisplayRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceDisplayCommandService {
    @NonNull
    private final ServiceDisplayRepository serviceDisplayRepository;
    @NonNull
    private final ServiceDisplayMapper serviceDisplayMapper;

    public void save(Long serviceId, List<ServiceDisplay> serviceDisplays) {
        List<ServiceDisplayEntity> serviceDisplayList = serviceDisplays.stream()
                .map(serviceDisplay -> serviceDisplayMapper.toEntity(serviceDisplay, serviceId)).toList();
        serviceDisplayRepository.saveAll(serviceDisplayList);
    }

    public void update(Long serviceId, List<ServiceDisplay> newServiceDisplays) {
        List<ServiceDisplayEntity> serviceDisplays = serviceDisplayRepository.findAllByServiceId(serviceId);
        serviceDisplayRepository.deleteAll(serviceDisplays);
        List<ServiceDisplayEntity> saveServiceDisplays = newServiceDisplays.stream()
                .map(serviceDisplay -> serviceDisplayMapper.toEntity(serviceDisplay, serviceId)).toList();
        serviceDisplayRepository.saveAll(saveServiceDisplays);
    }
}
