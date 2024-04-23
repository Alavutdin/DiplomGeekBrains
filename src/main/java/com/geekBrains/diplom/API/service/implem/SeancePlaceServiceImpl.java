package com.geekBrains.diplom.API.service.implem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.SeancePlaceDto;
import com.geekBrains.diplom.API.dto.factory.SeancePlaceDtoFactory;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.service.SeancePlaceService;
import com.geekBrains.diplom.STORE.entity.SeanceEntity;
import com.geekBrains.diplom.STORE.repository.SeancePlaceRepository;
import com.geekBrains.diplom.STORE.repository.SeanceRepository;

@Service
public class SeancePlaceServiceImpl implements SeancePlaceService {
    private final SeancePlaceRepository seancePlaceDao;
    private final SeancePlaceDtoFactory seancePlaceDtoFactory;
    private final SeanceRepository seanceDao;

    @Autowired
    public SeancePlaceServiceImpl(SeancePlaceRepository seancePlaceDao, SeancePlaceDtoFactory seancePlaceDtoFactory,
                                  SeanceRepository seanceDao) {
        super();
        this.seancePlaceDao = seancePlaceDao;
        this.seancePlaceDtoFactory = seancePlaceDtoFactory;
        this.seanceDao = seanceDao;
    }

    @Override
    public List<SeancePlaceDto> getSeancePlacesBySeance(Long seanceId) {
        return seancePlaceDtoFactory
                .createListSeancePlaceDto(
                        findSeanceById(seanceId)
                                .getSeancePlaces()
                                .stream()
                                .sorted((o1, o2)->o1.getPlace().getNumber()-o2.getPlace().getNumber())
                                .collect(Collectors.toList()));
    }


    private SeanceEntity findSeanceById(Long seanceId) {
        return seanceDao.findById(seanceId).orElseThrow(()->
                new NotFoundException(String.format("Сеанс с ID \"%s\" не найден", seanceId)));
    }



}
