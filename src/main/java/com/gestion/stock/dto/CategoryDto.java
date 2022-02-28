package com.gestion.stock.dto;

import com.gestion.stock.model.Article;
import com.gestion.stock.model.Category;
import com.gestion.stock.utils.StateEnum;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private String designation;

    private List<ArticleDto> articles;

    public CategoryDto fromEntity(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .articles(
                        category
                                .getArticles()
                                .stream()
                                .map(this::getArticleDtoFromEntity)
                                .collect(Collectors.toList())
                )
                .designation(category.getDesignation())
                .state(category.getState())
                .build();
    }

    public Category toEntity(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return null;
        }

        return Category.builder()
                .id(categoryDto.getId())
                .code(categoryDto.getCode())
                .articles(
                        categoryDto
                                .getArticles()
                                .stream()
                                .map(this::getArticleToEntity)
                                .collect(Collectors.toList())
                )
                .designation(categoryDto.getDesignation())
                .state(categoryDto.getState())
                .build();
    }

    private Article getArticleToEntity(ArticleDto articleDto) {
        return ArticleDto
                .builder()
                .build()
                .toEntity(articleDto);
    }

    private ArticleDto getArticleDtoFromEntity(Article article) {
        return ArticleDto
                .builder()
                .build()
                .fromEntity(article);
    }
}
