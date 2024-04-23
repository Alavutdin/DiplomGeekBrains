package com.geekBrains.diplom.API.dto.factory;

import com.geekBrains.diplom.API.dto.TicketDto;
import com.geekBrains.diplom.STORE.entity.SeancePlace;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketDtoFactory {
    public TicketDto createTicketDto(SeancePlace entity) {
        return new TicketDto(
                entity.getId(),
                entity.getClient().getNumClient(),
                entity.getPlace().getNumber(),
                entity.getSeance().getFilm().getTitle(),
                entity.getPlace().getHall().getName(),
                entity.getSeance().getStartedAt(),
                entity.getSeance().getPrice());
    }

    public List<TicketDto> createListTicketDto(List<SeancePlace> entities) {
        return entities
                .stream()
                .map(this::createTicketDto)
                .collect(Collectors.toList());
    }

}
