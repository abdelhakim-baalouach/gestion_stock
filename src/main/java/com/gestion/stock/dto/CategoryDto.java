package com.gestion.stock.dto;

import com.gestion.stock.model.Category;
import com.gestion.stock.utils.StateEnum;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private String designation;

    private List<ArticleDto> articles;

    private Integer idEntreprise;

    public CategoryDto fromEntity(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .state(category.getState())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }

    public Category toEntity(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return null;
        }

        return Category.builder()
                .id(categoryDto.getId())
                .code(categoryDto.getCode())
                .designation(categoryDto.getDesignation())
                .state(categoryDto.getState())
                .idEntreprise(categoryDto.getIdEntreprise())
                .build();
    }
}
