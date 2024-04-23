package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}
