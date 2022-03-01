package com.gestion.stock.services;

import com.gestion.stock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll(Integer id);

    void delete(Integer id);
}
