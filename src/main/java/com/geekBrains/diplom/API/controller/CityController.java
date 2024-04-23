package com.geekBrains.diplom.API.controller;

import com.geekBrains.diplom.API.dto.CityDto;
import com.geekBrains.diplom.API.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        super();
        this.cityService = cityService;
    }

    public static final String GET_LIST_CITY = "/api/cities";
    public static final String GET_CITY = "/api/cities/{cityId}";
    public static final String ADD_CITY = "/api/cities";
    public static final String DELETE_CITY = "/api/cities/{cityId}";
    public static final String UPDATE_CITY = "/api/cities/{cityId}";

    @GetMapping(GET_LIST_CITY)
    public ResponseEntity<List<CityDto>> getListCity() {
        return ResponseEntity.ok(cityService.getListCity());
    }

    @GetMapping(GET_CITY)
    public ResponseEntity<CityDto> getCity(@PathVariable("cityId") Long cityId) {
        return ResponseEntity.ok(cityService.getCity(cityId));
    }

    @PostMapping(ADD_CITY)
    public ResponseEntity<?> addCity(@RequestParam("name") String name) {
        return new ResponseEntity<>(cityService.createCity(name), HttpStatus.CREATED);
    }

    @DeleteMapping(DELETE_CITY)
    public ResponseEntity<?> deleteCity(@PathVariable("cityId") Long cityId) {
        cityService.deleteCity(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(UPDATE_CITY)
    public ResponseEntity<?> updateCity(@PathVariable("cityId") Long cityId,
                                        @RequestParam("name") String name) {
        return new ResponseEntity<>(cityService.updateCity(cityId, name), HttpStatus.ACCEPTED);
    }

}
