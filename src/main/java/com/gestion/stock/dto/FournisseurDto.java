package com.gestion.stock.dto;

import com.gestion.stock.model.CommandeFournisseur;
import com.gestion.stock.model.Fournisseur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (Objects.isNull(fournisseur)) {
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .state(fournisseur.getState())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .telephone(fournisseur.getTelephone())
                .commandeFournisseurs(
                        fournisseur
                                .getCommandeFournisseurs()
                                .stream()
                                .map(this::getFromEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (Objects.isNull(fournisseurDto)) {
            return null;
        }

        return Fournisseur.builder()
                .id(fournisseurDto.getId())
                .state(fournisseurDto.getState())
                .nom(fournisseurDto.getNom())
                .prenom(fournisseurDto.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .toEntity(fournisseurDto.getAdresse()))
                .photo(fournisseurDto.getPhoto())
                .mail(fournisseurDto.getMail())
                .telephone(fournisseurDto.getTelephone())
                .commandeFournisseurs(
                        fournisseurDto
                                .getCommandeFournisseurs()
                                .stream()
                                .map(this::getToEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private CommandeFournisseur getToEntity(CommandeFournisseurDto commandeFournisseurDto) {
        return CommandeFournisseurDto
                .builder()
                .build()
                .toEntity(commandeFournisseurDto);
    }

    private CommandeFournisseurDto getFromEntity(CommandeFournisseur commandeFournisseur) {
        return CommandeFournisseurDto
                .builder()
                .build()
                .fromEntity(commandeFournisseur);
    }
}
