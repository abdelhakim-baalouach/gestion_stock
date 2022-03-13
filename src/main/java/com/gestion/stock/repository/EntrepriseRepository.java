package com.gestion.stock.repository;

import com.gestion.stock.model.Entreprise;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByIdAndState(Integer id, StateEnum stateEnum);
}
