package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;

@Entity
public class SeancePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private SeanceEntity seance;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PlaceEntity place;
    private boolean reserved = false;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ClientEntity client;

    public SeancePlace() {
        super();
    }

    public SeancePlace(SeanceEntity seance, PlaceEntity place) {
        super();
        this.seance = seance;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeanceEntity getSeance() {
        return seance;
    }

    public void setSeance(SeanceEntity seance) {
        this.seance = seance;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }


}