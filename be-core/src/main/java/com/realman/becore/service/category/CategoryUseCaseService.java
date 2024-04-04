package com.realman.becore.service.category;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.category.Category;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryUseCaseService {
    @NonNull
    private final CategoryCommandService categoryCommandService;
    @NonNull
    private final CategoryQueryService categoryQueryService;

    @Transactional
    public void saveDefault() {
        categoryCommandService.saveDefault();
    }

    public Category findById(Long categoryId) {
        return categoryQueryService.findById(categoryId);
    }

    public List<Category> findAll() {
        return categoryQueryService.findAll();
    }

    public List<Category> findCategoryFields() {
        return categoryQueryService.findCategoryFields();
    }
}
