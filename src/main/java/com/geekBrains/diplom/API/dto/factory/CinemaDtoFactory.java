package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.CinemaDto;
import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaDtoFactory {
    public CinemaDto createCinemaDto(CinemaEntity entity) {
        return new CinemaDto(
                entity.getCinemaId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCity().getName(),
                entity.getStreet(),
                entity.getStructure(),
                entity.getStructure());
    }

    public List<CinemaDto> createListCinemaDto(List<CinemaEntity> entities) {
        return entities
                .stream()
                .map(this::createCinemaDto)
                .collect(Collectors.toList());
    }

}
