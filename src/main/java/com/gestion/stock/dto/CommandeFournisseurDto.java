package com.gestion.stock.dto;

import com.gestion.stock.model.CommandeFournisseur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class CommandeFournisseurDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private Integer idEntreprise;

    public CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (Objects.isNull(commandeFournisseur)) {
            return null;
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .state(commandeFournisseur.getState())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(
                        FournisseurDto
                                .builder()
                                .build()
                                .fromEntity(commandeFournisseur.getFournisseur())
                )
                .build();
    }

    public CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
        if (Objects.isNull(commandeFournisseurDto)) {
            return null;
        }

        return CommandeFournisseur.builder()
                .id(commandeFournisseurDto.getId())
                .state(commandeFournisseurDto.getState())
                .idEntreprise(commandeFournisseurDto.getIdEntreprise())
                .code(commandeFournisseurDto.getCode())
                .dateCommande(commandeFournisseurDto.getDateCommande())
                .fournisseur(
                        FournisseurDto
                                .builder()
                                .build()
                                .toEntity(commandeFournisseurDto.getFournisseur())
                )
                .build();
    }
}
