package com.gestion.stock.services.impl;

import com.gestion.stock.dto.FournisseurDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Fournisseur;
import com.gestion.stock.repository.FournisseurRepository;
import com.gestion.stock.services.FournisseurService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.utils.StateEnum;
import com.gestion.stock.validator.FournisseurValidator;
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
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        this.checkFournisseurDtoValid(dto);
        var fournisseur = this.fournisseurRepository.save(this.mapToFournisseur(dto)) ;
        return this.mapToFournisseurDto(fournisseur);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID fournisseur est null");
            return null;
        }
        return this.fournisseurRepository
                .findByIdAndState(id, StateEnum.ACTIVE)
                .map(this::mapToFournisseurDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun fournisseur avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public List<FournisseurDto> findAll(Integer id) {
        return this.fournisseurRepository
                .findAll()
                .stream()
                .map(this::mapToFournisseurDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var fournisseurDto = this.findById(id);
        this.fournisseurRepository.delete(this.mapToFournisseur(fournisseurDto));
    }

    private void checkFournisseurDtoValid(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Fournisseur n'est pas valide {}", dto);
            throw new InvalidEntityException("Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
    }

    private Fournisseur mapToFournisseur(FournisseurDto dto) {
        return FournisseurDto.builder()
                .build()
                .toEntity(dto);
    }

    private FournisseurDto mapToFournisseurDto(Fournisseur fournisseur) {
        return FournisseurDto.builder()
                .build()
                .fromEntity(fournisseur);
    }
}
