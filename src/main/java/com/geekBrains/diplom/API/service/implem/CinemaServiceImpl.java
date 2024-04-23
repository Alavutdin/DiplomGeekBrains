package com.geekBrains.diplom.API.service.implem;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.CinemaDto;
import com.geekBrains.diplom.API.dto.factory.CinemaDtoFactory;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.model.CinemaModel;
import com.geekBrains.diplom.API.service.CinemaService;
import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import com.geekBrains.diplom.STORE.entity.CityEntity;
import com.geekBrains.diplom.STORE.repository.CinemaRepository;
import com.geekBrains.diplom.STORE.repository.CityRepository;

@Service
public class CinemaServiceImpl implements CinemaService{

    private final CinemaRepository cinemaDao;
    private final CityRepository cityDao;
    private final CinemaDtoFactory cinemaDtoFactory;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaDao, CityRepository cityDao, CinemaDtoFactory cinemaDtoFactory) {
        super();
        this.cinemaDao = cinemaDao;
        this.cityDao = cityDao;
        this.cinemaDtoFactory = cinemaDtoFactory;
    }

    @Override
    public List<CinemaDto> getCinemasByCity(Long cityId) {
        return cinemaDtoFactory
                .createListCinemaDto(
                        findCityById(cityId)
                                .getCinemas());
    }

    @Override
    public CinemaDto addCinema(CinemaModel model, Long cityId) {
        return cinemaDtoFactory
                .createCinemaDto(
                        cinemaDao.saveAndFlush(
                                new CinemaEntity(
                                        model.getName(),
                                        model.getDescription(),
                                        findCityById(cityId),
                                        model.getStreet(),
                                        model.getStructure(),
                                        model.getFrame())));
    }

    @Override
    public void deleteCinema(Long cinemaId) {
        cinemaDao.deleteById(
                findCinemaById(cinemaId)
                        .getCinemaId());
    }

    @Override
    @Transactional
    public CinemaDto updateCinemas(CinemaModel model, Long cinemaId) {
        CinemaEntity cinema = findCinemaById(cinemaId);
        cinema.setDescription(model.getDescription());
        cinema.setFrame(model.getFrame());
        cinema.setName(model.getName());
        cinema.setStreet(model.getName());
        cinema.setStructure(model.getStructure());
        return cinemaDtoFactory
                .createCinemaDto(cinema);
    }

    private CityEntity findCityById(Long cityId) {
        return cityDao.findById(cityId).orElseThrow(()->
                new NotFoundException(
                        String.format("Город с ID \"%s\" не найден", cityId)));
    }

    private CinemaEntity findCinemaById(Long cinemaId) {
        return cinemaDao.findById(cinemaId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Кинотеатр с ID \"%s\" не найден",
                                cinemaId)));
    }

}
