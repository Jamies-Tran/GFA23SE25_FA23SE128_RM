package com.realman.becore.dto.shop_owner;

import lombok.Builder;

@Builder
public record ShopOwner(
                Long shopOwnerId,
                Long accountId) {

}
