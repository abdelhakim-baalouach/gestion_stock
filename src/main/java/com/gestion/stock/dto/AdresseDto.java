package com.gestion.stock.dto;

import com.gestion.stock.model.Adresse;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class AdresseDto {

    private String adresse1;

    private String adresse2;

    private String ville;

    private String codePostale;

    private String pays;

    public AdresseDto fromEntity(Adresse adresse) {
        if (Objects.isNull(adresse)) {
            return null;
        }

        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostale(adresse.getCodePostale())
                .pays(adresse.getPays())
                .build();
    }

    public Adresse toEntity(AdresseDto adresseDto) {
        if (Objects.isNull(adresseDto)) {
            return null;
        }

        return Adresse.builder()
                .adresse1(adresseDto.getAdresse1())
                .adresse2(adresseDto.getAdresse2())
                .ville(adresseDto.getVille())
                .codePostale(adresseDto.getCodePostale())
                .pays(adresseDto.getPays())
                .build();
    }
}
