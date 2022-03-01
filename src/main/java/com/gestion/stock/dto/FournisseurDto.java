package com.gestion.stock.dto;

import com.gestion.stock.model.Fournisseur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Builder
@Data
public class FournisseurDto {

    private Integer id;

    private StateEnum state;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String telephone;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    private Integer idEntreprise;

    public FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (Objects.isNull(fournisseur)) {
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .state(fournisseur.getState())
                .idEntreprise(fournisseur.getIdEntreprise())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .fromEntity(fournisseur.getAdresse())
                )
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .telephone(fournisseur.getTelephone())
                .build();
    }

    public Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (Objects.isNull(fournisseurDto)) {
            return null;
        }

        return Fournisseur.builder()
                .id(fournisseurDto.getId())
                .state(fournisseurDto.getState())
                .idEntreprise(fournisseurDto.getIdEntreprise())
                .nom(fournisseurDto.getNom())
                .prenom(fournisseurDto.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .toEntity(fournisseurDto.getAdresse())
                )
                .photo(fournisseurDto.getPhoto())
                .mail(fournisseurDto.getMail())
                .telephone(fournisseurDto.getTelephone())
                .build();
    }
}
