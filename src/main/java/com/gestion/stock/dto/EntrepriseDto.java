package com.gestion.stock.dto;

import com.gestion.stock.model.Entreprise;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private StateEnum state;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String mail;

    private String telephone;

    private String siteWeb;

    private List<UtilisateurDto> utilisateurs;

    public EntrepriseDto fromEntity(Entreprise entreprise) {
        if (Objects.isNull(entreprise)) {
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .state(entreprise.getState())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .fromEntity(entreprise.getAdresse())
                )
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .mail(entreprise.getMail())
                .telephone(entreprise.getTelephone())
                .siteWeb(entreprise.getSiteWeb())
                .build();
    }

    public Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if (Objects.isNull(entrepriseDto)) {
            return null;
        }

        return Entreprise.builder()
                .id(entrepriseDto.getId())
                .state(entrepriseDto.getState())
                .nom(entrepriseDto.getNom())
                .description(entrepriseDto.getDescription())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .toEntity(entrepriseDto.getAdresse())
                )
                .codeFiscal(entrepriseDto.getCodeFiscal())
                .photo(entrepriseDto.getPhoto())
                .mail(entrepriseDto.getMail())
                .telephone(entrepriseDto.getTelephone())
                .siteWeb(entrepriseDto.getSiteWeb())
                .build();
    }
}
