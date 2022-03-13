package com.gestion.stock.repository;

import com.gestion.stock.model.Article;
import com.gestion.stock.utils.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCodeAndState(String code, StateEnum stateEnum);

    Optional<Article> findByIdAndState(Integer id, StateEnum stateEnum);
}
