package com.realman.becore.service.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.services.models.BranchId;
import com.realman.becore.controller.api.services.models.ServiceId;
import com.realman.becore.dto.branch.service.BranchService;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.dto.service.ShopServiceInfo;
import com.realman.becore.dto.service.ShopServiceMapper;
import com.realman.becore.dto.service.ShopServiceSearchCriteria;
import com.realman.becore.dto.service.display.ServiceDisplay;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.service.ShopServiceRepository;
import com.realman.becore.service.branch.service.BranchServiceUseCaseService;
import com.realman.becore.service.services.display.ServiceDisplayUseCaseService;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceQueryService {
        @NonNull
        private final ShopServiceRepository shopServiceRepository;
        @NonNull
        private final BranchServiceUseCaseService branchServiceUseCaseService;
        @NonNull
        private final ServiceDisplayUseCaseService serviceDisplayUseCaseService;
        @NonNull
        private final ShopServiceMapper shopServiceMapper;

        public Page<ShopService> findAll(ShopServiceSearchCriteria searchCriteria,
                        PageRequestCustom pageRequestCustom) {
                Page<ShopServiceInfo> shopServices = shopServiceRepository
                                .findAllInfo(searchCriteria.toLowerCase(), pageRequestCustom.pageRequest());
                Map<Long, List<ServiceDisplay>> serviceDisplayMap = serviceDisplayUseCaseService.findAll()
                                .stream().collect(Collectors.groupingBy(ServiceDisplay::serviceId));
                Map<Long, List<BranchService>> branchServiceMap = branchServiceUseCaseService.findAll().stream()
                                .collect(Collectors.groupingBy(BranchService::serviceId));
                return shopServices.map(shopService -> shopServiceMapper.toDto(shopService,
                                branchServiceMap.get(shopService.getServiceId()),
                                serviceDisplayMap.get(shopService.getServiceId())));
        }

        public ShopService findById(ServiceId serviceId) {
                ShopServiceInfo service = shopServiceRepository.findInfoById(serviceId.value())
                                .orElseThrow(ResourceNotFoundException::new);
                Map<Long, List<ServiceDisplay>> serviceDisplayMap = serviceDisplayUseCaseService
                                .findByServiceId(serviceId.value())
                                .stream().collect(Collectors.groupingBy(ServiceDisplay::serviceId));
                List<BranchService> branchServiceList = branchServiceUseCaseService
                                .findAllByServiceId(serviceId.value());
                return shopServiceMapper.toDto(service, branchServiceList, serviceDisplayMap.get(serviceId.value()));
        }

        public List<ShopService> findByCategoryId(Long categoryId) {
                Map<Long, List<ServiceDisplay>> serviceDisplayMap = serviceDisplayUseCaseService.findAll()
                                .stream().collect(Collectors.groupingBy(ServiceDisplay::serviceId));
                List<ShopService> categoryList = shopServiceRepository.findByCategoryId(categoryId).stream()
                                .map(shopService -> shopServiceMapper
                                                .toDto(shopService,
                                                                serviceDisplayMap.get(shopService.getServiceId())))
                                .toList();
                return categoryList;
        }

        public List<ShopService> findAll() {
                Map<Long, List<ServiceDisplay>> serviceDisplayMap = serviceDisplayUseCaseService.findAll()
                                .stream().collect(Collectors.groupingBy(ServiceDisplay::serviceId));
                List<ShopService> categoryList = shopServiceRepository.findAll().stream()
                                .map(shopService -> shopServiceMapper
                                                .toDto(shopService,
                                                                serviceDisplayMap.get(shopService.getServiceId())))
                                .toList();
                return categoryList;
        }

        public List<ShopService> findAllServiceField(BranchId branchId) {
                List<ShopService> serviceList = shopServiceRepository.findAllServiceField(branchId)
                                .stream().map(shopServiceMapper::toDto)
                                .toList();
                return serviceList;
        }

}
