package com.realman.becore.dto.category;

import com.realman.becore.dto.enums.ECategoryType;
import com.realman.becore.dto.service.ShopService;
import lombok.Builder;
import java.util.List;

@Builder
public record Category(
                Long categoryId,
                String categoryName,
                ECategoryType categoryType,
                List<ShopService> serviceList) {
}
