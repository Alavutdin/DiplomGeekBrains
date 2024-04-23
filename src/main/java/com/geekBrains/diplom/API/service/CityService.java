package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.CityDto;

import java.util.List;

public interface CityService {
    public List<CityDto> getListCity();

    public CityDto getCity(Long cityId);

    public CityDto createCity(String name);

    public void deleteCity(Long cityId);

    public CityDto updateCity(Long cityId, String name);
}
