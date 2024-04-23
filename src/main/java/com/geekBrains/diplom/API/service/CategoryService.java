package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategoriesByCinema(Long cinemaId);

    CategoryDto createNewCategory(Long cinemaId, String nameCategory);

    CategoryDto updateCategory(Long categoryId, String categoryName);

    void deleteCategory(Long categoryId);

}
