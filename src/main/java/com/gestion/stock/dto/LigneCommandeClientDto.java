package com.gestion.stock.dto;

import com.gestion.stock.model.LigneCommandeClient;
import com.gestion.stock.util.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
public class LigneCommandeClientDto {

    private Integer id;

    private StateEnum state;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto article;

    private CommandeClientDto commandeClient;

    public LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (Objects.isNull(ligneCommandeClient)) {
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .state(ligneCommandeClient.getState())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .fromEntity(ligneCommandeClient.getArticle())
                )
                .commandeClient(
                        CommandeClientDto
                                .builder()
                                .build()
                                .fromEntity(ligneCommandeClient.getCommandeClient())
                )
                .build();
    }

    public LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if (Objects.isNull(ligneCommandeClientDto)) {
            return null;
        }

        return LigneCommandeClient.builder()
                .id(ligneCommandeClientDto.getId())
                .state(ligneCommandeClientDto.getState())
                .quantite(ligneCommandeClientDto.getQuantite())
                .prixUnitaire(ligneCommandeClientDto.getPrixUnitaire())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .toEntity(ligneCommandeClientDto.getArticle())
                )
                .commandeClient(
                        CommandeClientDto
                                .builder()
                                .build()
                                .toEntity(ligneCommandeClientDto.getCommandeClient())
                )
                .build();
    }
}
