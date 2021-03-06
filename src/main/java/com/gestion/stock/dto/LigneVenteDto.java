package com.gestion.stock.dto;

import com.gestion.stock.model.LigneVente;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
public class LigneVenteDto {

    private Integer id;

    private StateEnum state;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private VentesDto ventes;

    private ArticleDto article;

    private Integer idEntreprise;

    public LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (Objects.isNull(ligneVente)) {
            return null;
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .state(ligneVente.getState())
                .idEntreprise(ligneVente.getIdEntreprise())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .fromEntity(ligneVente.getArticle())
                )
                .ventes(
                        VentesDto
                                .builder()
                                .build()
                                .fromEntity(ligneVente.getVentes())
                )
                .build();
    }

    public LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (Objects.isNull(ligneVenteDto)) {
            return null;
        }

        return LigneVente.builder()
                .id(ligneVenteDto.getId())
                .state(ligneVenteDto.getState())
                .idEntreprise(ligneVenteDto.getIdEntreprise())
                .quantite(ligneVenteDto.getQuantite())
                .prixUnitaire(ligneVenteDto.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .toEntity(ligneVenteDto.getArticle())
                )
                .ventes(
                        VentesDto
                                .builder()
                                .build()
                                .toEntity(ligneVenteDto.getVentes())
                )
                .build();
    }
}
