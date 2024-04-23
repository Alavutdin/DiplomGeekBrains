package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    @Column(length = 100)
    private String name;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "city")
    private List<CinemaEntity> cinemas = new ArrayList<>();

    public CityEntity() {
        super();
    }

    public CityEntity(String name) {
        super();
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CinemaEntity> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<CinemaEntity> cinemas) {
        this.cinemas = cinemas;
    }


}
