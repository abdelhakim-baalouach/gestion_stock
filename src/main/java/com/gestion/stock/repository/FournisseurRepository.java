package com.gestion.stock.repository;

import com.gestion.stock.model.Fournisseur;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    Optional<Fournisseur> findByIdAndState(Integer id, StateEnum stateEnum);
}
