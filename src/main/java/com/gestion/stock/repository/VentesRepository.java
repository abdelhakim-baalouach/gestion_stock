package com.gestion.stock.repository;

import com.gestion.stock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentesRepository extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findByIdAndState_Active(Integer id);

    Optional<Ventes> findVentesByCodeAndState_Active(String code);
}
