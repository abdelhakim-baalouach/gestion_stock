package com.gestion.stock.dto;

import com.gestion.stock.model.Article;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

    public ArticleDto fromEntity(Article article) {
        if (Objects.isNull(article)) {
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .code(article.getCode())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .category(
                        CategoryDto
                                .builder()
                                .build()
                                .fromEntity(article.getCategory())
                )
                .state(category.getState())
                .build();
    }

    public Article toEntity(ArticleDto ArticleDto) {
        if (Objects.isNull(ArticleDto)) {
            return null;
        }

        return Article.builder()
                .id(ArticleDto.getId())
                .code(ArticleDto.getCode())
                .designation(ArticleDto.getDesignation())
                .prixUnitaireHt(ArticleDto.getPrixUnitaireHt())
                .tauxTva(ArticleDto.getTauxTva())
                .prixUnitaireTtc(ArticleDto.getPrixUnitaireTtc())
                .photo(ArticleDto.getPhoto())
                .category(
                        CategoryDto
                        .builder()
                        .build()
                        .toEntity(ArticleDto.getCategory())
                )
                .state(ArticleDto.getState())
                .build();
    }
}
