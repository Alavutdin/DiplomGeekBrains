package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.HallDto;
import com.geekBrains.diplom.API.model.HallModel;

import java.util.List;

public interface HallService {

    List<HallDto> getHallsByCinema(Long cinemaId);

    HallDto createNewHall(Long cinemaId, HallModel hall);

    HallDto updateHall(Long hallId, HallModel hall);

    void deleteHall(Long hallId);

}
