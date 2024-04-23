package com.geekBrains.diplom.STORE.repository;

import com.geekBrains.diplom.STORE.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByNumClient(String numClient);

}
