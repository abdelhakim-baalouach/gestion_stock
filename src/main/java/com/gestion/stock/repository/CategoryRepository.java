package com.gestion.stock.repository;

import com.gestion.stock.model.Category;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByIdAndState(Integer id, StateEnum stateEnum);

    Optional<Category> findCategoryByCodeAndState(String code, StateEnum stateEnum);
}
