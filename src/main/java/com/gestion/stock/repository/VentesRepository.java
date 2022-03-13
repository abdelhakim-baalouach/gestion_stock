package com.gestion.stock.repository;

import com.gestion.stock.model.Ventes;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentesRepository extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findByIdAndState(Integer id, StateEnum stateEnum);

    Optional<Ventes> findVentesByCodeAndState(String code, StateEnum stateEnum);
}
