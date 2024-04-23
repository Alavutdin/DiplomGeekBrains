package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.SeanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<SeanceEntity, Long> {

    Optional<SeanceEntity> findBySeanceIdAndStartedAtAfter(Long seanceId, LocalDateTime now);

}
