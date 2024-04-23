package com.geekBrains.diplom.API.service.implem;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.CategoryDto;
import com.geekBrains.diplom.API.dto.factory.CategoryDtoFactory;
import com.geekBrains.diplom.API.exception.BadRequestException;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.service.CategoryService;
import com.geekBrains.diplom.STORE.entity.CategoryEntity;
import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import com.geekBrains.diplom.STORE.repository.CategoryRepository;
import com.geekBrains.diplom.STORE.repository.CinemaRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryDao;
    private final CinemaRepository cinemaDao;
    private final CategoryDtoFactory categoryDtoFactory;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryDao, CinemaRepository cinemaDao,
                               CategoryDtoFactory categoryDtoFactory) {
        super();
        this.categoryDao = categoryDao;
        this.cinemaDao = cinemaDao;
        this.categoryDtoFactory = categoryDtoFactory;
    }

    @Override
    public List<CategoryDto> getCategoriesByCinema(Long cinemaId) {
        return categoryDtoFactory
                .createListCategoryDto(
                        findCinemaById(cinemaId)
                                .getCategories());
    }

    @Override
    public CategoryDto createNewCategory(Long cinemaId, String nameCategory) {
        CinemaEntity cinema = findCinemaById(cinemaId);
        findCategoryByCinemaAndNameIsPresentThrow(cinema, nameCategory.strip());
        return categoryDtoFactory
                .createCategoryDto(
                        categoryDao.saveAndFlush(
                                new CategoryEntity(
                                        nameCategory,
                                        cinema)));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long categoryId, String categoryName) {
        CategoryEntity category = findCategoryById(categoryId);
        if(!category.getName().equalsIgnoreCase(categoryName.strip())) {
            findCategoryByCinemaAndNameIsPresentThrow(category.getCinema(), categoryName);
            category.setName(categoryName);
        }
        return categoryDtoFactory.createCategoryDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryDao.deleteById(
                findCategoryById(categoryId).getCategoryId());
    }


    private CinemaEntity findCinemaById(Long cinemaId) {
        return cinemaDao.findById(cinemaId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Кинотеатр с ID \"%s\" не найден",
                                cinemaId)));
    }

    private boolean findCategoryByCinemaAndNameIsPresentThrow(CinemaEntity cinema, String name) {
        categoryDao.findByCinemaAndNameIgnoreCase(cinema, name).ifPresent((c)->{
            throw new BadRequestException(String.format("Категория с именем \"%s\"  уже существует"));
        });
        return false;
    }

    private CategoryEntity findCategoryById(Long categoryId) {
        return categoryDao.findById(categoryId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Категория с ID \"%s\" не найдена",
                                categoryId)));
    }

}
