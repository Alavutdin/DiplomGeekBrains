package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

}
