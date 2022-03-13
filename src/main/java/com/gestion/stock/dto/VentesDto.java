package com.gestion.stock.dto;

import com.gestion.stock.model.Ventes;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;

    public VentesDto fromEntity(Ventes ventes) {
        if (Objects.isNull(ventes)) {
            return null;
        }

        return VentesDto.builder()
                .id(ventes.getId())
                .state(ventes.getState())
                .idEntreprise(ventes.getIdEntreprise())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .build();
    }

    public Ventes toEntity(VentesDto ventesDto) {
        if (Objects.isNull(ventesDto)) {
            return null;
        }

        return Ventes.builder()
                .id(ventesDto.getId())
                .state(ventesDto.getState())
                .idEntreprise(ventesDto.getIdEntreprise())
                .code(ventesDto.getCode())
                .dateVente(ventesDto.getDateVente())
                .commentaire(ventesDto.getCommentaire())
                .build();
    }
}
