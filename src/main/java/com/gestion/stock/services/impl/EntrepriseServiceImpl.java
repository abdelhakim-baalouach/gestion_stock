package com.gestion.stock.services.impl;

import com.gestion.stock.dto.EntrepriseDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Entreprise;
import com.gestion.stock.repository.EntrepriseRepository;
import com.gestion.stock.services.EntrepriseService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.utils.StateEnum;
import com.gestion.stock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        this.checkEntrepriseDtoValid(dto);
        var entreprise = this.entrepriseRepository.save(this.mapToEntreprise(dto)) ;
        return this.mapToEntrepriseDto(entreprise);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID entreprise est null");
            return null;
        }
        return this.entrepriseRepository
                .findByIdAndState(id, StateEnum.ACTIVE)
                .map(this::mapToEntrepriseDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun entreprise avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.ENTREPRISE_NOT_FOUND)
                );
    }

    @Override
    public List<EntrepriseDto> findAll(Integer id) {
        return this.entrepriseRepository
                .findAll()
                .stream()
                .map(this::mapToEntrepriseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var entrepriseDto = this.findById(id);
        this.entrepriseRepository.delete(this.mapToEntreprise(entrepriseDto));
    }

    private void checkEntrepriseDtoValid(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("L'entreprise n'est pas valide {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
    }

    private Entreprise mapToEntreprise(EntrepriseDto dto) {
        return EntrepriseDto.builder()
                .build()
                .toEntity(dto);
    }

    private EntrepriseDto mapToEntrepriseDto(Entreprise entreprise) {
        return EntrepriseDto.builder()
                .build()
                .fromEntity(entreprise);
    }
}
