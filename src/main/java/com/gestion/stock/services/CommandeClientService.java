package com.gestion.stock.services;

import com.gestion.stock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll(Integer id);

    void delete(Integer id);
}
