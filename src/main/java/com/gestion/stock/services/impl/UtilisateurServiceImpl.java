package com.gestion.stock.services.impl;

import com.gestion.stock.dto.UtilisateurDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Utilisateur;
import com.gestion.stock.repository.UtilisateurRepository;
import com.gestion.stock.services.UtilisateurService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.UtilisateurValidator;
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
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        this.checkUtilisateurDtoValid(dto);
        var utilisateur = this.utilisateurRepository.save(this.mapToUtilisateur(dto)) ;
        return this.mapToUtilisateurDto(utilisateur);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID utilisateur est null");
            return null;
        }
        return this.utilisateurRepository
                .findByIdAndState_Active(id)
                .map(this::mapToUtilisateurDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun utilisateur avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public List<UtilisateurDto> findAll(Integer id) {
        return this.utilisateurRepository
                .findAll()
                .stream()
                .map(this::mapToUtilisateurDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var utilisateurDto = this.findById(id);
        this.utilisateurRepository.delete(this.mapToUtilisateur(utilisateurDto));
    }

    private void checkUtilisateurDtoValid(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("L'utilisateur n'est pas valide {}", dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
    }

    private Utilisateur mapToUtilisateur(UtilisateurDto dto) {
        return UtilisateurDto.builder()
                .build()
                .toEntity(dto);
    }

    private UtilisateurDto mapToUtilisateurDto(Utilisateur utilisateur) {
        return UtilisateurDto.builder()
                .build()
                .fromEntity(utilisateur);
    }
}
