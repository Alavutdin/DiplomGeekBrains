package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.FilmDto;
import com.geekBrains.diplom.STORE.entity.FilmCategory;
import com.geekBrains.diplom.STORE.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmDtoFactory {

    private final CategoryDtoFactory categoryDtoFactory;

    @Autowired
    public FilmDtoFactory(CategoryDtoFactory categoryDtoFactory) {
        super();
        this.categoryDtoFactory = categoryDtoFactory;
    }

    public FilmDto createFilmDto(FilmEntity entity) {
        return new FilmDto(
                entity.getFilmId(),
                entity.getTitle(),
                entity.getDescrition(),
                entity.getImage(),
                entity.getDuration(),
                entity.getDirector(),
                categoryDtoFactory
                        .createListCategoryDto(
                                entity.getFilmCategory()
                                        .stream()
                                        .map(FilmCategory::getCategory)
                                        .collect(Collectors.toList())));
    }

    public List<FilmDto> createListFilmDto(List<FilmEntity> entities) {

        return entities
                .stream()
                .map(this::createFilmDto)
                .collect(Collectors.toList());
    }
}
