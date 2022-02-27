package com.gestion.stock.dto;

import com.gestion.stock.model.CommandeClient;
import com.gestion.stock.model.LigneCommandeClient;
import com.gestion.stock.util.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
public class CommandeClientDto {

    private Integer id;

    private StateEnum state;

    private String code;

    private OffsetDateTime dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (Objects.isNull(commandeClient)) {
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .state(commandeClient.getState())
                .dateCommande(commandeClient.getDateCommande())
                .client(
                        ClientDto
                                .builder()
                                .build()
                                .fromEntity(commandeClient.getClient())
                )
                .ligneCommandeClients(
                        commandeClient
                                .getLigneCommandeClients()
                                .stream()
                                .map(this::getFromEntity)
                                .collect(Collectors.toList())
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
                .dateCommande(commandeClientDto.getDateCommande())
                .client(
                        ClientDto
                                .builder()
                                .build()
                                .toEntity(commandeClientDto.getClient())
                )
                .ligneCommandeClients(
                        commandeClientDto
                                .getLigneCommandeClients()
                                .stream()
                                .map(this::getToEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private LigneCommandeClient getToEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        return LigneCommandeClientDto
                .builder()
                .build()
                .toEntity(ligneCommandeClientDto);
    }

    private LigneCommandeClientDto getFromEntity(LigneCommandeClient ligneCommandeClient) {
        return LigneCommandeClientDto
                .builder()
                .build()
                .fromEntity(ligneCommandeClient);
    }
}
