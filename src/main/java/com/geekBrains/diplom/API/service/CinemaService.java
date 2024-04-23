package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.CinemaDto;
import com.geekBrains.diplom.API.model.CinemaModel;

import java.util.List;

public interface CinemaService {

    public List<CinemaDto> getCinemasByCity(Long cityId);

    public CinemaDto addCinema(CinemaModel model, Long cityId);

    public void deleteCinema(Long cinemaId);

    public CinemaDto updateCinemas(CinemaModel model, Long cinemaId);


}
