package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.SeanceDto;
import com.geekBrains.diplom.API.model.SeanceModel;

public interface SeanceService {

    SeanceDto createSeanceInTheHall(SeanceModel model, Long hallId);

    SeanceDto updateSeance(Long seanceId, SeanceModel model);

    void deleteSeance(Long seanceId);
}