package com.realman.becore.controller.api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.services.models.BranchId;
import com.realman.becore.controller.api.services.models.ShopServiceFieldResponse;
import com.realman.becore.controller.api.services.models.ShopServiceModelMapper;
import com.realman.becore.controller.api.services.models.ShopServiceRequest;
import com.realman.becore.controller.api.services.models.ShopServiceResponse;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.dto.service.ShopServiceSearchCriteria;
import com.realman.becore.service.services.ShopServiceUseCaseService;
import com.realman.becore.util.response.ListResponse;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopServicesController implements ShopServicesAPI {
    @NonNull
    private final ShopServiceUseCaseService shopServiceUseCaseService;
    @NonNull
    private final ShopServiceModelMapper shopServiceModelMapper;

    @Override
    public void save(@Valid ShopServiceRequest shopServiceRequest) {
        ShopService shopService = shopServiceModelMapper.toDto(shopServiceRequest);
        shopServiceUseCaseService.save(shopService);
    }

    @Override
    public PageImplResponse<ShopServiceResponse> findAll(String search, Long branchId,
            @Min(1) Integer current, String sorter, Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current);
        ShopServiceSearchCriteria searchCriteria = ShopServiceSearchCriteria.of(search, branchId);
        Page<ShopService> shopServices = shopServiceUseCaseService.findAll(searchCriteria, pageRequestCustom);
        Page<ShopServiceResponse> responses = shopServices.map(shopServiceModelMapper::toModel);
        return new PageImplResponse<>(responses.getContent(), responses.getTotalElements(), responses.getTotalPages(),
                pageSize, current);
    }

    @Override
    public ListResponse<ShopServiceFieldResponse> findAllServiceField(Long branchId) {
        List<ShopServiceFieldResponse> responses = shopServiceUseCaseService.findAllServiceField(new BranchId(branchId))
                .stream()
                .map(shopServiceModelMapper::toFieldModel).toList();
        return new ListResponse<>(responses);
    }

}
