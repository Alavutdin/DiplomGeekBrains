package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

}

