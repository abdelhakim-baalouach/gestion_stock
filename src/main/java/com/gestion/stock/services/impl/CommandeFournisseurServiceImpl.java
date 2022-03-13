package com.gestion.stock.services.impl;

import com.gestion.stock.dto.CommandeFournisseurDto;
import com.gestion.stock.dto.LigneCommandeFournisseurDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.*;
import com.gestion.stock.repository.*;
import com.gestion.stock.services.CommandeFournisseurService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(
            CommandeFournisseurRepository commandeFournisseurRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
            FournisseurRepository fournisseurRepository,
            ArticleRepository articleRepository
    ) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        this.checkCommandeFournisseurDtoValid(dto);
        Optional<Fournisseur> fournisseur = this.fournisseurRepository.findByIdAndState_Active(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur avec ID {} n'ete trouve dans la BDD", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avac l'ID = " +  dto.getFournisseur().getId() + " n'ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (Objects.nonNull(dto.getLigneCommandeFournisseurs())) {
            dto.getLigneCommandeFournisseurs()
                    .forEach(ligneCommandeFournisseurDto -> {
                        if (Objects.nonNull(ligneCommandeFournisseurDto.getArticle())) {
                            Optional<Article> article = this.articleRepository.findByIdAndState_Active(ligneCommandeFournisseurDto.getArticle().getId());
                            if (article.isEmpty()) {
                                articleErrors.add("L'article avec l'ID " + ligneCommandeFournisseurDto.getArticle().getId() + " n'existe pas");
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

        var commandeFournisseurSaved = this.commandeFournisseurRepository.save(this.mapToCommandeFournisseur(dto));

        List<LigneCommandeFournisseur> ligneCommandeFournisseurs =
                dto.getLigneCommandeFournisseurs()
                        .stream()
                        .map(ligneCommandeFournisseurDto -> this.getLigneCommandeFournisseur(ligneCommandeFournisseurDto, commandeFournisseurSaved))
                        .collect(Collectors.toList());

        this.ligneCommandeFournisseurRepository.saveAll(ligneCommandeFournisseurs);

        return this.mapToCommandeFournisseurDto(commandeFournisseurSaved);
    }

    private LigneCommandeFournisseur getLigneCommandeFournisseur(LigneCommandeFournisseurDto ligneCommandeFournisseurDto, CommandeFournisseur commandeFournisseurSaved) {
        var ligneCommandeFournisseur = LigneCommandeFournisseurDto.builder().build().toEntity(ligneCommandeFournisseurDto);
        ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseurSaved);
        return ligneCommandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID commande fournisseur est null");
            return null;
        }
        return this.commandeFournisseurRepository
                .findByIdAndState_Active(id)
                .map(this::mapToCommandeFournisseurDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun commande fournisseur avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (Objects.isNull(code)) {
            log.error("Code commande fournisseur  est null");
            return null;
        }
        return this.commandeFournisseurRepository
                .findCommandeFournisseurByCodeAndState_Active(code)
                .map(this::mapToCommandeFournisseurDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun commande fournisseur avec le CODE = " + code + " n'ete trouve dans la BDD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public List<CommandeFournisseurDto> findAll(Integer id) {
        return this.commandeFournisseurRepository
                .findAll()
                .stream()
                .map(this::mapToCommandeFournisseurDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var commandeClientDto = this.findById(id);
        this.commandeFournisseurRepository.delete(this.mapToCommandeFournisseur(commandeClientDto));
    }

    private void checkCommandeFournisseurDtoValid(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Fournisseur n'est pas valide {}", dto);
            throw new InvalidEntityException("Commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
    }

    private CommandeFournisseur mapToCommandeFournisseur(CommandeFournisseurDto dto) {
        return CommandeFournisseurDto.builder()
                .build()
                .toEntity(dto);
    }

    private CommandeFournisseurDto mapToCommandeFournisseurDto(CommandeFournisseur commandeFournisseur) {
        return CommandeFournisseurDto.builder()
                .build()
                .fromEntity(commandeFournisseur);
    }
}
