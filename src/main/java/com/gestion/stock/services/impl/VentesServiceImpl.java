package com.gestion.stock.services.impl;

import com.gestion.stock.dto.CommandeFournisseurDto;
import com.gestion.stock.dto.LigneVenteDto;
import com.gestion.stock.dto.VentesDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.*;
import com.gestion.stock.repository.ArticleRepository;
import com.gestion.stock.repository.LigneVenteRepository;
import com.gestion.stock.repository.VentesRepository;
import com.gestion.stock.services.VentesService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.utils.StateEnum;
import com.gestion.stock.validator.VenteValidator;
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
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Ventes n'est pas valide {}", dto);
            throw new InvalidEntityException("Ventes n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = this.articleRepository.findByIdAndState(ligneVenteDto.getArticle().getId(), StateEnum.ACTIVE);
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'ete trouve dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()) {
            log.error("Un ou plusieurs articles n'ont pas ete trouve dans la BDD {}", articleErrors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes ventesSaved = this.ventesRepository.save(this.mapToVentes(dto));

        List<LigneVente> ligneVentes =
                dto.getLigneVentes()
                        .stream()
                        .map(ligneVenteDto -> this.getLigneVente(ligneVenteDto, ventesSaved))
                        .collect(Collectors.toList());

        this.ligneVenteRepository.saveAll(ligneVentes);


        return this.mapToVentesDto(ventesSaved);
    }

    private LigneVente getLigneVente(LigneVenteDto ligneVenteDto, Ventes ventesSaved) {
        var ligneVente = LigneVenteDto.builder().build().toEntity(ligneVenteDto);
        ligneVente.setVentes(ventesSaved);
        return ligneVente;
    }

    @Override
    public VentesDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID vente est null");
            return null;
        }
        return this.ventesRepository
                .findByIdAndState(id, StateEnum.ACTIVE)
                .map(this::mapToVentesDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun vente avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public VentesDto findByCode(String code) {
        if (Objects.isNull(code)) {
            log.error("Code vente  est null");
            return null;
        }
        return this.ventesRepository
                .findVentesByCodeAndState(code, StateEnum.ACTIVE)
                .map(this::mapToVentesDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun vente avec le CODE = " + code + " n'ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public List<VentesDto> findAll(Integer id) {
        return this.ventesRepository
                .findAll()
                .stream()
                .map(this::mapToVentesDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var ventesDto = this.findById(id);
        this.ventesRepository.delete(this.mapToVentes(ventesDto));
    }

    private Ventes mapToVentes(VentesDto dto) {
        return VentesDto.builder()
                .build()
                .toEntity(dto);
    }

    private VentesDto mapToVentesDto(Ventes ventes) {
        return VentesDto.builder()
                .build()
                .fromEntity(ventes);
    }
}
