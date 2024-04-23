package com.geekBrains.diplom.API.service.implem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.service.CinemaInitService;
import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import com.geekBrains.diplom.STORE.entity.CityEntity;
import com.geekBrains.diplom.STORE.repository.CinemaRepository;
import com.geekBrains.diplom.STORE.repository.CityRepository;

@Service
public class CinemaInitServiceImpl implements CinemaInitService{

    private final CityRepository cityDao;
    private final CinemaRepository cinemaDao;

    public CinemaInitServiceImpl(CityRepository cityDao, CinemaRepository cinemaDao) {
        super();
        this.cityDao = cityDao;
        this.cinemaDao = cinemaDao;
    }

    @Override
    public void initCities() {
        List<CityEntity> cities = List.of(
                new CityEntity("Санкт-Петербург"),
                new CityEntity("Москва"),
                new CityEntity("Краснодар"),
                new CityEntity("Буйнакск"));
        cities.stream().forEach(cityDao::save);
    }

    @Override
    public void initCinema() {
    }

    @Override
    public void initCategory() {
    }

}
