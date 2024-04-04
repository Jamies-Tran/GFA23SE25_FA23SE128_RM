package com.realman.becore.dto.category;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.repository.database.category.CategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryEntity toEntity(Category dto);

    Category toDto(CategoryEntity entity);

    @Mapping(source = "serviceList", target = "serviceList")
    Category toDto(CategoryEntity entity, List<ShopService> serviceList);
}
