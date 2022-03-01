package com.gestion.stock.validator;

import com.gestion.stock.dto.EntrepriseDto;
import com.gestion.stock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(dto)) {
            errors.add("Veuillez renseigner le nom d'entreprise");
            errors.add("Veuillez renseigner le telephone d'entreprise");
            errors.add("Veuillez renseigner le l'adresse d'entreprise");
            return errors;
        }

        if ( !StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez renseigner le nom d'entreprise");
        }

        if ( !StringUtils.hasLength(dto.getTelephone())) {
            errors.add("Veuillez renseigner le telephone d'entreprise");
        }

        if ( Objects.isNull(dto.getAdresse())) {
            errors.add("Veuillez renseigner le l'adresse d'entreprise");
        } else {
            if ( !StringUtils.hasLength(dto.getAdresse().getAdresse1())) {
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }

            if ( !StringUtils.hasLength(dto.getAdresse().getVille())) {
                errors.add("Le champs 'Ville' est obligatoire");
            }

            if ( !StringUtils.hasLength(dto.getAdresse().getCodePostale())) {
                errors.add("Le champs 'Code postale' est obligatoire");
            }

            if ( !StringUtils.hasLength(dto.getAdresse().getPays())) {
                errors.add("Le champs 'Pays' est obligatoire");
            }
        }

        return errors;
    }
}
