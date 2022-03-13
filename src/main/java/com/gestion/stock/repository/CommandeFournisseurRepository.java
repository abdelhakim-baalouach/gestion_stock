package com.gestion.stock.repository;

import com.gestion.stock.model.CommandeFournisseur;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCodeAndState(String code, StateEnum stateEnum);

    Optional<CommandeFournisseur> findByIdAndState(Integer id, StateEnum stateEnum);
}
