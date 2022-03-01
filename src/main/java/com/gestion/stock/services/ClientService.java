package com.gestion.stock.services;

import com.gestion.stock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll(Integer id);

    void delete(Integer id);
}
