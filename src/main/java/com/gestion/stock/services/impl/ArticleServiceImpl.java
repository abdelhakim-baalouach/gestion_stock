package com.gestion.stock.services.impl;

import com.gestion.stock.dto.ArticleDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Article;
import com.gestion.stock.repository.ArticleRepository;
import com.gestion.stock.services.ArticleService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        this.checkArticleDtoIsValid(dto);
        var article = this.articleRepository.save(this.mapToArticle(dto)) ;
        return this.mapToArticleDto(article);
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID article est null");
            return null;
        }
        return this.articleRepository
                .findByIdAndState_Active(id)
                .map(this::mapToArticleDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun article avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public ArticleDto findByCode(String code) {
        if (Objects.isNull(code)) {
            log.error("Code article est null");
            return null;
        }
        return this.articleRepository
                .findArticleByCodeAndState_Active(code)
                .map(this::mapToArticleDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun article avec le CODE = " + code + " n'ete trouve dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public List<ArticleDto> findAll() {
        return this.articleRepository
                .findAll()
                .stream()
                .map(this::mapToArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var articleDto = this.findById(id);
        this.articleRepository.delete(this.mapToArticle(articleDto));
    }

    private void checkArticleDtoIsValid(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("L'article n'est pas valide {}", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
    }

    private Article mapToArticle(ArticleDto dto) {
        return ArticleDto.builder()
                .build()
                .toEntity(dto);
    }

    private ArticleDto mapToArticleDto(Article article) {
        return ArticleDto.builder()
                .build()
                .fromEntity(article);
    }
}
