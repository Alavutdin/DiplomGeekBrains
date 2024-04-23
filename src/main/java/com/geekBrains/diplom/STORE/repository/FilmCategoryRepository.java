package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.CategoryEntity;
import com.geekBrains.diplom.STORE.entity.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {

    List<FilmCategory> findAllByCategory(CategoryEntity category);

}
