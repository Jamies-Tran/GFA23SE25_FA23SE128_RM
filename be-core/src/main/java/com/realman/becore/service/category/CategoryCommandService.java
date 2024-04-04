package com.realman.becore.service.category;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.realman.becore.dto.category.CategoryMapper;
import com.realman.becore.dto.enums.ECategoryType;
import com.realman.becore.repository.database.category.CategoryEntity;
import com.realman.becore.repository.database.category.CategoryRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryCommandService {
    @NonNull
    private final CategoryRepository categoryRepository;
    @NonNull
    private final CategoryMapper categoryMapper;

    public void saveDefault() {
        List<CategoryEntity> existCategoryEntities = categoryRepository.findAll();
        if (Objects.isNull(existCategoryEntities) || existCategoryEntities.isEmpty()) {
            List<CategoryEntity> categoryEntities = List.of(
                    CategoryEntity.builder().categoryName(ECategoryType.MASSAGE.getName())
                            .categoryType(ECategoryType.MASSAGE).priority(ECategoryType.MASSAGE.getPriority()).build(),
                    CategoryEntity.builder().categoryName(ECategoryType.HAIRCUT.getName())
                            .categoryType(ECategoryType.HAIRCUT).priority(ECategoryType.HAIRCUT.getPriority()).build(),
                    CategoryEntity.builder().categoryName(ECategoryType.OTHER.getName())
                            .categoryType(ECategoryType.OTHER).priority(ECategoryType.OTHER.getPriority()).build());
            categoryRepository.saveAll(categoryEntities);
        }
    }
}
