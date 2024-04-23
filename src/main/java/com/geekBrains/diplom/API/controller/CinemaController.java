package com.geekBrains.diplom.API.controller;

import com.geekBrains.diplom.API.dto.CinemaDto;
import com.geekBrains.diplom.API.exception.BadRequestException;
import com.geekBrains.diplom.API.model.CinemaModel;
import com.geekBrains.diplom.API.service.CinemaService;
import com.geekBrains.diplom.API.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaController {


    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService, CityService cityService) {
        super();
        this.cinemaService = cinemaService;
    }

    public static final String GET_CINEMAS_BY_CITY = "/api/cities/{cityId}/cinemas";
    public static final String ADD_CINEMA_IN_THE_CITY = "/api/cities/{cityId}/cinemas";
    public static final String DELETE_CINEMA = "/api/cities/cinemas/{cinemaId}";
    public static final String UPDATE_CINEMA = "/api/cities/cinemas/{cinemaId}";


    @GetMapping(GET_CINEMAS_BY_CITY)
    public ResponseEntity<List<CinemaDto>> getCinemasByCity(@PathVariable("cityId") Long cityId) {
        return ResponseEntity.ok(cinemaService.getCinemasByCity(cityId));
    }

    @PostMapping(ADD_CINEMA_IN_THE_CITY)
    public ResponseEntity<CinemaDto> addCinemaInTheCity(
            @PathVariable("cityId") Long cityId, @Valid CinemaModel model, BindingResult result) {
        checkError(result);
        return new ResponseEntity<>(
                cinemaService.addCinema(model, cityId),
                HttpStatus.CREATED);
    }

    @DeleteMapping(DELETE_CINEMA)
    public ResponseEntity<?> deleteCinema(@PathVariable("cinemaId") Long cinemaId) {
        cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(UPDATE_CINEMA)
    public ResponseEntity<CinemaDto> updateCinema(
            @Valid CinemaModel model, BindingResult result,
            @PathVariable("cinemaId") Long cinemaId) {
        checkError(result);
        return new ResponseEntity<>(
                cinemaService.updateCinemas(model, cinemaId),
                HttpStatus.ACCEPTED);
    }


    private boolean checkError(BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors().toString());
        }
        return true;
    }


}
