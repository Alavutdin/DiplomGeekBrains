package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seance")
public class SeanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seanceId;
    private LocalDateTime startedAt;
    private LocalDateTime stopedAt;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private HallEntity hall;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private FilmEntity film;
    private Double price;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "seance")
    private List<SeancePlace> seancePlaces = new ArrayList<>();

    public SeanceEntity() {
        super();
    }

    public SeanceEntity(LocalDateTime startedAt, LocalDateTime stopedAt, HallEntity hall, FilmEntity film, Double price) {
        super();
        this.startedAt = startedAt;
        this.stopedAt = stopedAt;
        this.hall = hall;
        this.film = film;
        this.price = price;
    }

    public Long getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Long seanceId) {
        this.seanceId = seanceId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getStopedAt() {
        return stopedAt;
    }

    public void setStopedAt(LocalDateTime stopedAt) {
        this.stopedAt = stopedAt;
    }

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
    }

    public FilmEntity getFilm() {
        return film;
    }

    public void setFilm(FilmEntity film) {
        this.film = film;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SeancePlace> getSeancePlaces() {
        return seancePlaces;
    }

    public void setSeancePlaces(List<SeancePlace> seancePlaces) {
        this.seancePlaces = seancePlaces;
    }

}
