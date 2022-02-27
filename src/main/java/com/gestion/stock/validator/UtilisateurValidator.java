package com.gestion.stock.validator;

import com.gestion.stock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(utilisateurDto)) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'email d'utilisateur");
            errors.add("Veuillez renseigner l'username d'utilisateur");
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
            errors.add("Veuillez renseigner le l'adresse d'utilisateur");

            return errors;
        }

        if ( !StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }

        if ( !StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }

        if ( !StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email d'utilisateur");
        }

        if ( !StringUtils.hasLength(utilisateurDto.getUsername())) {
            errors.add("Veuillez renseigner l'username d'utilisateur");
        }

        if ( !StringUtils.hasLength(utilisateurDto.getPassword())) {
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }

        if ( Objects.isNull(utilisateurDto.getDateNaissance())) {
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
        }

        if ( Objects.isNull(utilisateurDto.getAdresse())) {
            errors.add("Veuillez renseigner le l'adresse d'utilisateur");
        } else {
            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }

            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("Le champs 'Ville' est obligatoire");
            }

            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                errors.add("Le champs 'Code postale' est obligatoire");
            }

            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("Le champs 'Pays' est obligatoire");
            }
        }

        return errors;
    }
}
