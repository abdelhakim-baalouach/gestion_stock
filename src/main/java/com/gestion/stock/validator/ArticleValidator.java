package com.gestion.stock.validator;

import com.gestion.stock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(articleDto)) {
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la designation de l'article");
            errors.add("Veuillez renseigner le prix unitaire de l'article");
            errors.add("Veuillez renseigner le taux TVA de l'article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veuillez selectionner une categorie");

            return errors;
        }

        if ( !StringUtils.hasLength(articleDto.getCode())) {
            errors.add("Veuillez renseigner le code de l'article");
        }

        if ( !StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("Veuillez renseigner la designation de l'article");
        }

        if ( Objects.isNull(articleDto.getPrixUnitaireHt())) {
            errors.add("Veuillez renseigner le prix unitaire de l'article");
        }

        if ( Objects.isNull(articleDto.getTauxTva())) {
            errors.add("Veuillez renseigner le taux TVA de l'article");
        }

        if ( Objects.isNull(articleDto.getPrixUnitaireTtc())) {
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
        }

        if ( Objects.isNull(articleDto.getCategory())) {
            errors.add("Veuillez selectionner une categorie");
        }

        return errors;
    }
}
