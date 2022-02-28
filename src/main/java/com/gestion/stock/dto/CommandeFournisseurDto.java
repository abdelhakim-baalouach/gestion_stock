package com.gestion.stock.dto;

import com.gestion.stock.model.CommandeFournisseur;
import com.gestion.stock.model.LigneCommandeFournisseur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
public class CommandeFournisseurDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (Objects.isNull(commandeFournisseur)) {
            return null;
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .state(commandeFournisseur.getState())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(
                        FournisseurDto
                                .builder()
                                .build()
                                .fromEntity(commandeFournisseur.getFournisseur())
                )
                .ligneCommandeFournisseurs(
                        commandeFournisseur
                                .getLigneCommandeFournisseurs()
                                .stream()
                                .map(this::getFromEntity)
                                .collect(Collectors.toList())
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
                .code(commandeFournisseurDto.getCode())
                .dateCommande(commandeFournisseurDto.getDateCommande())
                .fournisseur(
                        FournisseurDto
                                .builder()
                                .build()
                                .toEntity(commandeFournisseurDto.getFournisseur())
                )
                .ligneCommandeFournisseurs(
                        commandeFournisseurDto
                                .getLigneCommandeFournisseurs()
                                .stream()
                                .map(this::getToEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private LigneCommandeFournisseur getToEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        return LigneCommandeFournisseurDto
                .builder()
                .build()
                .toEntity(ligneCommandeFournisseurDto);
    }

    private LigneCommandeFournisseurDto getFromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        return LigneCommandeFournisseurDto
                .builder()
                .build()
                .fromEntity(ligneCommandeFournisseur);
    }
}
