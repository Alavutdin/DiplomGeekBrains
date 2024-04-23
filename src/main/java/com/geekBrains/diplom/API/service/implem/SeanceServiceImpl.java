package com.geekBrains.diplom.API.service.implem;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.SeanceDto;
import com.geekBrains.diplom.API.dto.factory.SeanceDtoFactory;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.model.SeanceModel;
import com.geekBrains.diplom.API.service.SeanceService;
import com.geekBrains.diplom.STORE.entity.FilmEntity;
import com.geekBrains.diplom.STORE.entity.HallEntity;
import com.geekBrains.diplom.STORE.entity.SeanceEntity;
import com.geekBrains.diplom.STORE.entity.SeancePlace;
import com.geekBrains.diplom.STORE.repository.FilmRepository;
import com.geekBrains.diplom.STORE.repository.HallRepository;
import com.geekBrains.diplom.STORE.repository.SeancePlaceRepository;
import com.geekBrains.diplom.STORE.repository.SeanceRepository;

@Service
public class SeanceServiceImpl implements SeanceService {
    private final SeanceDtoFactory seanceDtoFactory;
    private final SeanceRepository seanceDao;
    private final FilmRepository filmDao;
    private final HallRepository hallDao;
    private final SeancePlaceRepository seancePlaceDao;

    @Autowired
    public SeanceServiceImpl(SeanceDtoFactory seanceDtoFactory, SeanceRepository seanceDao, FilmRepository filmDao,
                             HallRepository hallDao, SeancePlaceRepository seancePlaceDao) {
        super();
        this.seanceDtoFactory = seanceDtoFactory;
        this.seanceDao = seanceDao;
        this.filmDao = filmDao;
        this.hallDao = hallDao;
        this.seancePlaceDao = seancePlaceDao;
    }

    @Override
    @Transactional
    public SeanceDto createSeanceInTheHall(SeanceModel model, Long hallId) { //проверка по времени проведения
        FilmEntity film = findFilmById(model.getFilmId());
        HallEntity hall = findHallById(hallId);
        SeanceEntity seance = seanceDao.saveAndFlush(
                new SeanceEntity(
                        model.getStartedAt(),
                        model.getStartedAt()
                                .plusMinutes(Math.round(
                                        film.getDuration()*60)),
                        hall,
                        film,
                        model.getPrice()));
        hall.getPlaces().stream().map((p)->{
            return new SeancePlace(seance, p);
        }).forEach((sp)->{
            seancePlaceDao.save(sp);
        });
        return seanceDtoFactory.createSeanceDto(seance);
    }

    @Override
    public SeanceDto updateSeance(Long seanceId, SeanceModel model) {
        return null;
    }

    @Override
    public void deleteSeance(Long seanceId) {
        seanceDao.deleteById(findSeanceById(seanceId).getSeanceId());
    }


    private SeanceEntity findSeanceById(Long seanceId) {
        return seanceDao.findById(seanceId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Сеанс с ID \"%s\" не найден",
                                seanceId)));
    }

    private FilmEntity findFilmById(Long filmId) {
        return filmDao.findById(filmId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Фильм с ID \"%s\" не найден",
                                filmId)));
    }

    private HallEntity findHallById(Long hallId) {
        return hallDao.findById(hallId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Холл с ID \"%s\" не найден",
                                hallId)));
    }

}
