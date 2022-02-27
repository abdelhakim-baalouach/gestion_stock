package com.gestion.stock.dto;

import com.gestion.stock.model.Ventes;
import com.gestion.stock.util.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateVente;

    private String commentaire;


    public VentesDto fromEntity(Ventes ventes) {
        if (Objects.isNull(ventes)) {
            return null;
        }

        return VentesDto.builder()
                .id(ventes.getId())
                .state(ventes.getState())
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
                .code(ventesDto.getCode())
                .dateVente(ventesDto.getDateVente())
                .commentaire(ventesDto.getCommentaire())
                .build();
    }
}
