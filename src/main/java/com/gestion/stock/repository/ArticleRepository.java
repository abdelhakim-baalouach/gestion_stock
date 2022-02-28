package com.gestion.stock.repository;

import com.gestion.stock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCodeAndState_Active(String code);

    Optional<Article> findByIdAndState_Active(Integer id);
}
