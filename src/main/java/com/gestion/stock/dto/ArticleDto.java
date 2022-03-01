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

    private Integer idEntreprise;

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
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    public Article toEntity(ArticleDto articleDto) {
        if (Objects.isNull(articleDto)) {
            return null;
        }

        return Article.builder()
                .id(articleDto.getId())
                .code(articleDto.getCode())
                .designation(articleDto.getDesignation())
                .prixUnitaireHt(articleDto.getPrixUnitaireHt())
                .tauxTva(articleDto.getTauxTva())
                .prixUnitaireTtc(articleDto.getPrixUnitaireTtc())
                .photo(articleDto.getPhoto())
                .category(
                        CategoryDto
                        .builder()
                        .build()
                        .toEntity(articleDto.getCategory())
                )
                .state(articleDto.getState())
                .idEntreprise(articleDto.getIdEntreprise())
                .build();
    }
}
