package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.SeancePlaceDto;

import java.util.List;

public interface SeancePlaceService {

    List<SeancePlaceDto> getSeancePlacesBySeance(Long seanceId);

}
