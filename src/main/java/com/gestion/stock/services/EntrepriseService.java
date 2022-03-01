package com.gestion.stock.services;

import com.gestion.stock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll(Integer id);

    void delete(Integer id);
}
