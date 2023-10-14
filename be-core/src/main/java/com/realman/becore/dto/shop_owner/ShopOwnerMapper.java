package com.realman.becore.dto.shop_owner;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.realman.becore.repository.database.shop_owner.ShopOwnerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopOwnerMapper {
    ShopOwner toDto(ShopOwnerEntity entity);

    ShopOwnerEntity toEntity(ShopOwner dto);
}
