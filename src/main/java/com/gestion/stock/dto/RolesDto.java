package com.gestion.stock.dto;

import com.gestion.stock.model.Roles;
import com.gestion.stock.util.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Builder
@Data
public class RolesDto {

    private Integer id;

    private StateEnum state;

    private String roleName;

    private UtilisateurDto utilisateur;

    public RolesDto fromEntity(Roles roles) {
        if (Objects.isNull(roles)) {
            return null;
        }

        return RolesDto.builder()
                .id(roles.getId())
                .state(roles.getState())
                .roleName(roles.getRoleName())
                .utilisateur(
                        UtilisateurDto
                                .builder()
                                .build()
                                .fromEntity(roles.getUtilisateur())
                )
                .build();
    }

    public Roles toEntity(RolesDto rolesDto) {
        if (Objects.isNull(rolesDto)) {
            return null;
        }

        return Roles.builder()
                .id(rolesDto.getId())
                .state(rolesDto.getState())
                .roleName(rolesDto.getRoleName())
                .utilisateur(
                        UtilisateurDto
                                .builder()
                                .build()
                                .toEntity(rolesDto.getUtilisateur())
                )
                .build();
    }
}
