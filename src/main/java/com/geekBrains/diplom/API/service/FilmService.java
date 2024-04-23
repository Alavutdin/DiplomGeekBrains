package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.FilmDto;
import com.geekBrains.diplom.API.model.FilmModel;

import java.util.List;

public interface FilmService {

    List<FilmDto> getFilmsByCategory(Long categoryId);

    FilmDto addFilmInTheCategories(FilmModel model);

    FilmDto updateFilm(FilmModel model, Long filmId);

    void deleteFilm(Long filmId);

}
