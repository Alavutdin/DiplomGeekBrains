package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.SeancePlaceDto;
import com.geekBrains.diplom.STORE.entity.SeancePlace;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeancePlaceDtoFactory {
    public SeancePlaceDto createSeancePlaceDto(SeancePlace entity) {
        return new SeancePlaceDto(
                entity.getId(),
                entity.isReserved(),
                entity.getPlace().getNumber());
    }

    public List<SeancePlaceDto> createListSeancePlaceDto(List<SeancePlace> list) {
        return list
                .stream()
                .map(this::createSeancePlaceDto)
                .collect(Collectors.toList());
    }
}
