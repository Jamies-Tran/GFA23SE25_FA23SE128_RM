package com.realman.becore.controller.api.category;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.category.models.CategoryModelMapper;
import com.realman.becore.controller.api.category.models.CategoryResponse;
import com.realman.becore.dto.category.Category;
import com.realman.becore.service.category.CategoryUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryAPI {
    @NonNull
    private final CategoryUseCaseService categoryUseCaseService;
    @NonNull
    private final CategoryModelMapper categoryModelMapper;

    @Override
    public ValueResponse<CategoryResponse> findById(Long categoryId) {
        Category category = categoryUseCaseService.findById(categoryId); 
        CategoryResponse response = categoryModelMapper.toModel(category);
        return new ValueResponse<>(response);
    }
    
}
