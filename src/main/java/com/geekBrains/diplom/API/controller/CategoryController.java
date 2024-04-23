package com.geekBrains.diplom.API.controller;

import com.geekBrains.diplom.API.dto.CategoryDto;
import com.geekBrains.diplom.API.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController — это составная аннотация, которая сама мета-аннотируется
 * аннотациями @Controller и @ResponseBody, указывая на контроллер
 * */

@RestController
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    public static final String GET_CATEGORIES_BY_CINEMA = "/api/cities/cinemas/{cinemaId}/categories";
    public static final String ADD_CATEGORY_IN_THE_CINEMA = "/api/cities/cinemas/{cinemaId}/categories";
    public static final String UPDATE_CATEGORY = "/api/cities/cinemas/categories/{categoryId}";
    public static final String DELETE_CATEGORY = "/api/cities/cinemas/categories/{categoryId}";


    /**@GetMapping — это специализированная аннотация @RequestMapping, предусмотренная конкретно
     * для обработки HTTP GET-запросов, что придаёт коду лаконичность и читаемость.
     * */
    @GetMapping(GET_CATEGORIES_BY_CINEMA)
    public ResponseEntity<List<CategoryDto>> getCategoriesByCinema(
            @PathVariable("cinemaId") Long cinemaId) {
        return ResponseEntity.ok(
                categoryService
                        .getCategoriesByCinema(cinemaId));
    }

    /**
     * Аннотация @PostMapping указывает, что данный метод обрабатывает все POST-запросы
     * */

    @PostMapping(ADD_CATEGORY_IN_THE_CINEMA)
    public ResponseEntity<CategoryDto> addCategoryInTheCinema(
            @PathVariable("cinemaId") Long cinemaId, @RequestParam String nameCategory) {
        return new ResponseEntity<>(
                categoryService.createNewCategory(cinemaId, nameCategory),
                HttpStatus.CREATED);
    }

    /**
     * Аннотация @PutMapping в String MVC Framework - мощный инструмент для обработки HTTP-запросов PUT
     * */
    @PutMapping(UPDATE_CATEGORY)
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("categoryId") Long categoryId, @RequestParam String categoryName) {
        return new ResponseEntity<>(
                categoryService.updateCategory(categoryId, categoryName),
                HttpStatus.ACCEPTED);
    }

    /**
     * Аннотация @DeleteMapping в Spring MVC - мощный инструмент для обработки HTTP-запросов на удаление
     * Он отображает определенный метод обработки URL-адресов,
     * позволяющий получать и обрабатывать данные,
     * отправленные с помощью запросов на удаление.
     * */
    @DeleteMapping(DELETE_CATEGORY)
    public ResponseEntity<?> deleteCategory(
            @PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
