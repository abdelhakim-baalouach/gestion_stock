package com.gestion.stock.services;

import com.gestion.stock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto dto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll(Integer id);

    void delete(Integer id);
}
