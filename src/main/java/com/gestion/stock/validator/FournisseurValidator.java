package com.gestion.stock.validator;

import com.gestion.stock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(fournisseurDto)) {
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner l'email du fournisseur");
            errors.add("Veuillez renseigner le numero de téléphone du fournisseur");
            return errors;
        }

        if ( !StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if ( !StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }

        if ( !StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner l'email du fournisseur");
        }

        if ( !StringUtils.hasLength(fournisseurDto.getTelephone())) {
            errors.add("Veuillez renseigner le numero de téléphone du fournisseur");
        }

        return errors;
    }
}
