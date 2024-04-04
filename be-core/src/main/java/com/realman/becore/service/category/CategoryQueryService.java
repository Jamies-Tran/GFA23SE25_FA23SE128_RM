package com.realman.becore.service.category;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.realman.becore.dto.category.Category;
import com.realman.becore.dto.category.CategoryMapper;
import com.realman.becore.dto.service.ShopService;
import com.realman.becore.dto.service.ShopServiceMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.category.CategoryEntity;
import com.realman.becore.repository.database.category.CategoryRepository;
import com.realman.becore.service.services.ShopServiceQueryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {
  @NonNull
  private final CategoryRepository categoryRepository;
  @NonNull
  private final CategoryMapper categoryMapper;
  @NonNull
  private final ShopServiceQueryService shopServiceQueryService;
  @NonNull
  private final ShopServiceMapper shopServiceMapper;

  public List<Category> findAll() {
    List<CategoryEntity> categoryList = categoryRepository.findAll();
    Map<Long, List<ShopService>> serviceMap = shopServiceQueryService.findAll().stream()
        .collect(Collectors.groupingBy(ShopService::categoryId));
    List<Category> categories = categoryList.stream()
        .map(category -> categoryMapper.toDto(category, serviceMap.get(category.getCategoryId()))).toList();
    return categories;
  }

  public Map<Long, Category> categoryMapById() {
    Map<Long, Category> categories = categoryRepository.findAll().stream().map(categoryMapper::toDto)
        .collect(Collectors.toMap(t -> t.categoryId(), t -> t));
    return categories;
  }

  public Category findById(Long categoryId) {
    CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
        .orElseThrow(ResourceNotFoundException::new);
    Map<Long, List<ShopService>> categoryMap = shopServiceQueryService.findByCategoryId(categoryId).stream()
        .collect(Collectors.groupingBy(ShopService::categoryId));
    return categoryMapper.toDto(categoryEntity, categoryMap.get(categoryId));
  }

  public List<Category> findCategoryFields() {
    List<Category> categoryList = categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    return categoryList;
  }
}
