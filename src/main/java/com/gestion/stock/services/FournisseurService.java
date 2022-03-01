package com.gestion.stock.services;

import com.gestion.stock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll(Integer id);

    void delete(Integer id);
}
