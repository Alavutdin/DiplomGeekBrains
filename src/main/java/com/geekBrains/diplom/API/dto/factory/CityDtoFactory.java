package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.CityDto;
import com.geekBrains.diplom.STORE.entity.CityEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDtoFactory {
    public CityDto createCityDto(CityEntity entity) {
        return new CityDto(
                entity.getCityId(),
                entity.getName());
    }

    public List<CityDto> createListCityDto(List<CityEntity> entities) {
        return entities
                .stream()
                .map(this::createCityDto)
                .collect(Collectors.toList());
    }
}