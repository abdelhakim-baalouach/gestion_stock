package com.gestion.stock.validator;

import com.gestion.stock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(clientDto)) {
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner le prenom du client");
            errors.add("Veuillez renseigner l'email du client");
            errors.add("Veuillez renseigner le numero de téléphone du client");
            return errors;
        }

        if ( !StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Veuillez renseigner le nom du client");
        }

        if ( !StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du client");
        }

        if ( !StringUtils.hasLength(clientDto.getMail())) {
            errors.add("Veuillez renseigner l'email du client");
        }

        if ( !StringUtils.hasLength(clientDto.getTelephone())) {
            errors.add("Veuillez renseigner le numero de téléphone du client");
        }

        return errors;
    }
}