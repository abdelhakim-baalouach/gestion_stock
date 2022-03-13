package com.gestion.stock.services.impl;

import com.gestion.stock.dto.CommandeClientDto;
import com.gestion.stock.dto.LigneCommandeClientDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Article;
import com.gestion.stock.model.Client;
import com.gestion.stock.model.CommandeClient;
import com.gestion.stock.model.LigneCommandeClient;
import com.gestion.stock.repository.ArticleRepository;
import com.gestion.stock.repository.ClientRepository;
import com.gestion.stock.repository.CommandeClientRepository;
import com.gestion.stock.repository.LigneCommandeClientRepository;
import com.gestion.stock.services.CommandeClientService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        this.checkCommandeClientDtoValid(dto);
        Optional<Client> client = this.clientRepository.findByIdAndState_Active(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client avec ID {} n'ete trouve dans la BDD", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avac l'ID = " +  dto.getClient().getId() + " n'ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (Objects.nonNull(dto.getLigneCommandeClients())) {
            dto.getLigneCommandeClients()
                    .forEach(ligneCommandeClientDto -> {
                        if (Objects.nonNull(ligneCommandeClientDto.getArticle())) {
                            Optional<Article> article = this.articleRepository.findByIdAndState_Active(ligneCommandeClientDto.getArticle().getId());
                            if (article.isEmpty()) {
                                articleErrors.add("L'article avec l'ID " + ligneCommandeClientDto.getArticle().getId() + " n'existe pas");
                            }
                        } else {
                            articleErrors.add("Impossible d'enregister une commande avec un article NULL");
                        }
                    });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Article n'existe pas dans la BDD");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient commandeClientSaved = this.commandeClientRepository.save(this.mapToCommandeClient(dto));

        List<LigneCommandeClient> ligneCommandeClients =
                dto.getLigneCommandeClients()
                    .stream()
                    .map(ligneCommandeClientDto -> this.getLigneCommandeClient(ligneCommandeClientDto, commandeClientSaved))
                    .collect(Collectors.toList());

        this.ligneCommandeClientRepository.saveAll(ligneCommandeClients);

        return this.mapToCommandeClientDto(commandeClientSaved);
    }

    private LigneCommandeClient getLigneCommandeClient(LigneCommandeClientDto ligneCommandeClientDto, CommandeClient commandeClientSaved) {
        var ligneCommandeClient = LigneCommandeClientDto.builder().build().toEntity(ligneCommandeClientDto);
        ligneCommandeClient.setCommandeClient(commandeClientSaved);
        return ligneCommandeClient;
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID commande client est null");
            return null;
        }
        return this.commandeClientRepository
                .findByIdAndState_Active(id)
                .map(this::mapToCommandeClientDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun commande client avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
                );
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (Objects.isNull(code)) {
            log.error("Code commande client  est null");
            return null;
        }
        return this.commandeClientRepository
                .findCommandeClientByCodeAndState_Active(code)
                .map(this::mapToCommandeClientDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun commande client avec le CODE = " + code + " n'ete trouve dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
                );
    }

    @Override
    public List<CommandeClientDto> findAll(Integer id) {
        return this.commandeClientRepository
                .findAll()
                .stream()
                .map(this::mapToCommandeClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var commandeClientDto = this.findById(id);
        this.commandeClientRepository.delete(this.mapToCommandeClient(commandeClientDto));
    }

    private void checkCommandeClientDtoValid(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande client n'est pas valide {}", dto);
            throw new InvalidEntityException("Commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }
    }

    private CommandeClient mapToCommandeClient(CommandeClientDto dto) {
        return CommandeClientDto.builder()
                .build()
                .toEntity(dto);
    }

    private CommandeClientDto mapToCommandeClientDto(CommandeClient commandeClient) {
        return CommandeClientDto.builder()
                .build()
                .fromEntity(commandeClient);
    }
}
