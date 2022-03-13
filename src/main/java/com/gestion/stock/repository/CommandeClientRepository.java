package com.gestion.stock.repository;

import com.gestion.stock.model.CommandeClient;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findByIdAndState(Integer id, StateEnum stateEnum);

    Optional<CommandeClient> findCommandeClientByCodeAndState(String code, StateEnum stateEnum);
}
