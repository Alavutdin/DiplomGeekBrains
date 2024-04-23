package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.ClientEntity;
import com.geekBrains.diplom.STORE.entity.SeancePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeancePlaceRepository extends JpaRepository<SeancePlace, Long> {

    List<SeancePlace> findAllByClient(ClientEntity client);

    Optional<SeancePlace> findByIdAndReserved(Long id, boolean b);

}
