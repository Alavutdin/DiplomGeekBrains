package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String name;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private CinemaEntity cinema;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "category")
    private List<FilmCategory> filmCategory = new ArrayList<>();

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(String name, CinemaEntity cinema) {
        super();
        this.name = name;
        this.cinema = cinema;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CinemaEntity getCinema() {
        return cinema;
    }

    public void setCinema(CinemaEntity cinema) {
        this.cinema = cinema;
    }

    public List<FilmCategory> getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(List<FilmCategory> filmCategory) {
        this.filmCategory = filmCategory;
    }

}
