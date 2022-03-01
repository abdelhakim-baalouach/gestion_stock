package com.gestion.stock.dto;

import com.gestion.stock.model.CommandeClient;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class CommandeClientDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private Integer idEntreprise;

    public CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (Objects.isNull(commandeClient)) {
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .state(commandeClient.getState())
                .idEntreprise(commandeClient.getIdEntreprise())
                .dateCommande(commandeClient.getDateCommande())
                .client(
                        ClientDto
                                .builder()
                                .build()
                                .fromEntity(commandeClient.getClient())
                )
                .build();
    }

    public CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if (Objects.isNull(commandeClientDto)) {
            return null;
        }

        return CommandeClient.builder()
                .id(commandeClientDto.getId())
                .state(commandeClientDto.getState())
                .idEntreprise(commandeClientDto.getIdEntreprise())
                .dateCommande(commandeClientDto.getDateCommande())
                .client(
                        ClientDto
                                .builder()
                                .build()
                                .toEntity(commandeClientDto.getClient())
                )
                .build();
    }
}
