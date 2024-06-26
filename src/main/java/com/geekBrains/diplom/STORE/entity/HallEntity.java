package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hall")
public class HallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;
    private String name;
    private int countPlace;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CinemaEntity cinema;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "hall")
    private List<PlaceEntity> places = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "hall")
    private List<SeanceEntity> seances = new ArrayList<>();


    public HallEntity() {
        super();
    }

    public HallEntity(String name, int countPlace, CinemaEntity cinema) {
        super();
        this.name = name;
        this.countPlace = countPlace;
        this.cinema = cinema;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }

    public CinemaEntity getCinema() {
        return cinema;
    }

    public void setCinema(CinemaEntity cinema) {
        this.cinema = cinema;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        this.places = places;
    }

    public List<SeanceEntity> getSeances() {
        return seances;
    }

    public void setSeances(List<SeanceEntity> seances) {
        this.seances = seances;
    }


}
