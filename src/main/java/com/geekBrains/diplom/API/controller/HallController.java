package com.geekBrains.diplom.API.controller;

import com.geekBrains.diplom.API.dto.HallDto;
import com.geekBrains.diplom.API.exception.BadRequestException;
import com.geekBrains.diplom.API.model.HallModel;
import com.geekBrains.diplom.API.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class HallController {


    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        super();
        this.hallService = hallService;
    }

    public static final String GET_HALLS_BY_CINEMA = "/api/cities/cinemas/{cinemaId}/halls";
    public static final String ADD_HALL_IN_THE_CINEMA = "/api/cities/cinemas/{cinemaId}/halls";
    public static final String UPDATE_HALL = "/api/cities/cinemas/halls/{hallId}";
    public static final String DELETE_HALL = "/api/cities/cinemas/halls/{hallId}";

    @GetMapping(GET_HALLS_BY_CINEMA)
    public ResponseEntity<List<HallDto>> getHallsByCinema(
            @PathVariable("cinemaId") Long cinemaId) {
        return ResponseEntity.ok(hallService.getHallsByCinema(cinemaId));
    }

    @PostMapping(ADD_HALL_IN_THE_CINEMA)
    public ResponseEntity<HallDto> addHallInTheCinema(
            @PathVariable("cinemaId") Long cinemaId, @Valid HallModel hall, BindingResult result) {
        checkError(result);
        return new ResponseEntity<>(hallService.createNewHall(cinemaId, hall), HttpStatus.CREATED);
    }

    @PutMapping(UPDATE_HALL)
    public ResponseEntity<HallDto> updateHall(
            @PathVariable("hallId") Long hallId, @Valid HallModel hall, BindingResult result) {
        checkError(result);
        return new ResponseEntity<>(
                hallService.updateHall(hallId, hall),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(DELETE_HALL)
    public ResponseEntity<?> deleteHall(@PathVariable("hallId") Long hallId) {
        hallService.deleteHall(hallId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private boolean checkError(BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(String.format("Ошибка при вводе данных %s", result.getAllErrors()));
        }
        return false;
    }


}
