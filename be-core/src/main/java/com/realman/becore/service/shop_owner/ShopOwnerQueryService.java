package com.realman.becore.service.shop_owner;

import org.springframework.stereotype.Service;

import com.realman.becore.dto.shop_owner.ShopOwner;
import com.realman.becore.dto.shop_owner.ShopOwnerMapper;
import com.realman.becore.enums.EErrorMessage;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.shop_owner.ShopOwnerEntity;
import com.realman.becore.repository.database.shop_owner.ShopOwnerRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopOwnerQueryService {
    @NonNull
    private final ShopOwnerRepository shopOwnerRepository;
    @NonNull
    private final ShopOwnerMapper shopOwnerMapper;

    public ShopOwner findByAccountId(Long accountId) {
        ShopOwnerEntity shopOwnerEntity = shopOwnerRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        EErrorMessage.SHOPOWNER_NOT_FOUND.name()));
        return shopOwnerMapper.toDto(shopOwnerEntity);
    }
}
