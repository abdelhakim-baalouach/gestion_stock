package com.gestion.stock.dto;

import com.gestion.stock.model.LigneCommandeFournisseur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
public class LigneCommandeFournisseurDto {

    private Integer id;

    private StateEnum state;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private Integer idEntreprise;

    public LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (Objects.isNull(ligneCommandeFournisseur)) {
            return null;
        }

        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .state(ligneCommandeFournisseur.getState())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .fromEntity(ligneCommandeFournisseur.getArticle())
                )
                .commandeFournisseur(
                        CommandeFournisseurDto
                                .builder()
                                .build()
                                .fromEntity(ligneCommandeFournisseur.getCommandeFournisseur())
                )
                .build();
    }

    public LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (Objects.isNull(ligneCommandeFournisseurDto)) {
            return null;
        }

        return LigneCommandeFournisseur.builder()
                .id(ligneCommandeFournisseurDto.getId())
                .state(ligneCommandeFournisseurDto.getState())
                .idEntreprise(ligneCommandeFournisseurDto.getIdEntreprise())
                .quantite(ligneCommandeFournisseurDto.getQuantite())
                .prixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .toEntity(ligneCommandeFournisseurDto.getArticle())
                )
                .commandeFournisseur(
                        CommandeFournisseurDto
                                .builder()
                                .build()
                                .toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur())
                )
                .build();
    }
}
