package com.realman.becore.controller.api.category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.category.models.CategoryResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category", description = "Category management API")
@RequestMapping("/v1/category/{categoryId}")
@PreAuthorize("hasAuthority('category:view')")
public interface CategoryAPI {
    @GetMapping
    ValueResponse<CategoryResponse> findById(@PathVariable Long categoryId);
}
