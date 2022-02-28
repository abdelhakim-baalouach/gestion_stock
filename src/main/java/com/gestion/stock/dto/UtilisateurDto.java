package com.gestion.stock.dto;

import com.gestion.stock.model.Roles;
import com.gestion.stock.model.Utilisateur;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private StateEnum state;

    private String nom;

    private String prenom;

    private Instant dateNaissance;

    private String email;

    private String telephone;

    private String username;

    private String password;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;

    public UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (Objects.isNull(utilisateur)) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .state(utilisateur.getState())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateNaissance(utilisateur.getDateNaissance())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .username(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .fromEntity(utilisateur.getAdresse())
                )
                .photo(utilisateur.getPhoto())
                .entreprise(
                        EntrepriseDto
                                .builder()
                                .build()
                                .fromEntity(utilisateur.getEntreprise())
                )
                .roles(
                        utilisateur
                                .getRoles()
                                .stream()
                                .map(this::getFromEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (Objects.isNull(utilisateurDto)) {
            return null;
        }

        return Utilisateur.builder()
                .id(utilisateurDto.getId())
                .state(utilisateurDto.getState())
                .nom(utilisateurDto.getNom())
                .prenom(utilisateurDto.getPrenom())
                .dateNaissance(utilisateurDto.getDateNaissance())
                .email(utilisateurDto.getEmail())
                .telephone(utilisateurDto.getTelephone())
                .username(utilisateurDto.getUsername())
                .password(utilisateurDto.getPassword())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .toEntity(utilisateurDto.getAdresse())
                )
                .photo(utilisateurDto.getPhoto())
                .entreprise(
                        EntrepriseDto
                                .builder()
                                .build()
                                .toEntity(utilisateurDto.getEntreprise())
                )
                .roles(
                        utilisateurDto
                                .getRoles()
                                .stream()
                                .map(this::getToEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private Roles getToEntity(RolesDto rolesDto) {
        return RolesDto
                .builder()
                .build()
                .toEntity(rolesDto);
    }

    private RolesDto getFromEntity(Roles roles) {
        return RolesDto
                .builder()
                .build()
                .fromEntity(roles);
    }
}
