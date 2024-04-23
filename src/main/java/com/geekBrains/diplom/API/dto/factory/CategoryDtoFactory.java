package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.CategoryDto;
import com.geekBrains.diplom.STORE.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoFactory {
    public CategoryDto createCategoryDto(CategoryEntity entity) {
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getName());
    }

    public List<CategoryDto> createListCategoryDto(List<CategoryEntity> entities) {
        return entities
                .stream()
                .map(this::createCategoryDto)
                .collect(Collectors.toList());
    }
}