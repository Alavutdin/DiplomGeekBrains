package com.geekBrains.diplom.API.service.implem;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.CityDto;
import com.geekBrains.diplom.API.dto.factory.CityDtoFactory;
import com.geekBrains.diplom.API.exception.BadRequestException;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.service.CityService;
import com.geekBrains.diplom.STORE.entity.CityEntity;
import com.geekBrains.diplom.STORE.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    private final CityDtoFactory cityDtoFactory;
    private final CityRepository cityDao;

    @Autowired
    public CityServiceImpl(CityDtoFactory cityDtoFactory, CityRepository cityDao) {
        super();
        this.cityDtoFactory = cityDtoFactory;
        this.cityDao = cityDao;
    }

    @Override
    public List<CityDto> getListCity() {
        return cityDtoFactory
                .createListCityDto(
                        cityDao.findAll());
    }

    @Override
    public CityDto getCity(Long cityId) {
        return cityDtoFactory
                .createCityDto(
                        findCityById(cityId));
    }

    @Override
    public CityDto createCity(String name) {
        findCityIsPresentThrow(name);
        return cityDtoFactory
                .createCityDto(
                        cityDao
                                .saveAndFlush(
                                        new CityEntity(name))
                );

    }

    @Override
    public void deleteCity(Long cityId) {
        cityDao.deleteById(
                findCityById(cityId)
                        .getCityId());
    }


    @Override
    @Transactional
    public CityDto updateCity(Long cityId, String name) {
        CityEntity city = findCityById(cityId);
        if(!city.getName().equalsIgnoreCase(name)) {
            if(!findCityIsPresentThrow(name)) {
                city.setName(name);
            }
        }
        return cityDtoFactory.createCityDto(city);
    }


    private CityEntity findCityById(Long cityId) {
        return cityDao.findById(cityId).orElseThrow(()->
                new NotFoundException(
                        String.format("Город с ID \"%s\" не найден", cityId)));
    }

    private boolean findCityIsPresentThrow(String name) {
        cityDao.findByName(name).ifPresent((c)->{
            throw new BadRequestException(
                    String.format("Город с названием \"%s\" уже создан", name));
        });
        return false;
    }






}
