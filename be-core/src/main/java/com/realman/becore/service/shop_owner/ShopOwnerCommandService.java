package com.realman.becore.service.shop_owner;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.shop_owner.ShopOwnerMapper;
import com.realman.becore.repository.database.shop_owner.ShopOwnerRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopOwnerCommandService {
    @NonNull
    private final ShopOwnerRepository shopOwnerRepository;
    @NonNull
    private final ShopOwnerMapper shopOwnerMapper;

    public void save(ShopOwner shopOwner) {
        shopOwnerRepository.save(shopOwnerMapper.toEntity(shopOwner));

    }
}
