package com.gestion.stock.repository;

import com.gestion.stock.model.Client;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByIdAndState(Integer id, StateEnum stateEnum);
}
