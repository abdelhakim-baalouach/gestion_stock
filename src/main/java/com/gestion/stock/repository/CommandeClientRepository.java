package com.gestion.stock.repository;

import com.gestion.stock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findByIdAndState_Active(Integer id);

    Optional<CommandeClient> findCommandeClientByCodeAndState_Active(String code);
}
