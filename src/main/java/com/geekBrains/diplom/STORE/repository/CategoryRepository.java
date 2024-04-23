package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.CategoryEntity;
import com.geekBrains.diplom.STORE.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    Optional<CategoryEntity> findByCinemaAndNameIgnoreCase(CinemaEntity cinema, String name);

}
