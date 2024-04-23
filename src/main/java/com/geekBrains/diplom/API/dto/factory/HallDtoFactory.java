package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.HallDto;
import com.geekBrains.diplom.STORE.entity.HallEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HallDtoFactory {
    private final SeanceDtoFactory seanceDtoFactory;

    @Autowired
    public HallDtoFactory(SeanceDtoFactory seanceDtoFactory) {
        super();
        this.seanceDtoFactory = seanceDtoFactory;
    }

    public HallDto createHallDto(HallEntity hall) {
        return new HallDto(
                hall.getHallId(),
                hall.getName(),
                seanceDtoFactory
                        .createListSeanceDto(hall.getSeances())
        );
    }

    public List<HallDto> creatListHallDto(List<HallEntity> halls) {
        return halls
                .stream()
                .map(this::createHallDto)
                .collect(Collectors.toList());
    }
}
