package com.realman.becore.service.shop_owner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.shop_owner.ShopOwner;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopOwnerUseCaseService {
    @NonNull
    private final ShopOwnerCommandService shopOwnerCommandService;
    @NonNull
    private final ShopOwnerQueryService shopOwnerQueryService;

    @Transactional
    public void save(ShopOwner shopOwner) {
        shopOwnerCommandService.save(shopOwner);
    }

    public ShopOwner findByAccountId(Long accountID) {
        return shopOwnerQueryService.findByAccountId(accountID);
    }
}
