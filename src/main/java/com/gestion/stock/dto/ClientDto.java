package com.gestion.stock.dto;

import com.gestion.stock.model.Client;
import com.gestion.stock.utils.StateEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Builder
@Data
public class ClientDto {

    private Integer id;

    private StateEnum state;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String telephone;

    private Integer idEntreprise;

    private List<CommandeClientDto> commandeClients;

    public ClientDto fromEntity(Client client) {
        if (Objects.isNull(client)) {
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .state(client.getState())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .fromEntity(client.getAdresse()))
                .photo(client.getPhoto())
                .mail(client.getMail())
                .telephone(client.getTelephone())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }

    public Client toEntity(ClientDto clientDto) {
        if (Objects.isNull(clientDto)) {
            return null;
        }

        return Client.builder()
                .id(clientDto.getId())
                .state(clientDto.getState())
                .nom(clientDto.getNom())
                .prenom(clientDto.getPrenom())
                .adresse(
                        AdresseDto
                                .builder()
                                .build()
                                .toEntity(clientDto.getAdresse()))
                .photo(clientDto.getPhoto())
                .mail(clientDto.getMail())
                .telephone(clientDto.getTelephone())
                .idEntreprise(clientDto.getIdEntreprise())
                .build();
    }
}
