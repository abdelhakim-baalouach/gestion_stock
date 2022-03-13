package com.gestion.stock.repository;

import com.gestion.stock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCodeAndState_Active(String code);

    Optional<CommandeFournisseur> findByIdAndState_Active(Integer id);
}
