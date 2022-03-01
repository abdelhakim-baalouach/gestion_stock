package com.gestion.stock.services.impl;

import com.gestion.stock.dto.ClientDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Client;
import com.gestion.stock.repository.ClientRepository;
import com.gestion.stock.services.ClientService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.ClientValidator;
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
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        this.checkClientDtoIsValid(dto);
        var client = this.clientRepository.save(this.mapToClient(dto)) ;
        return this.mapToClientDto(client);
    }

    @Override
    public ClientDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID client est null");
            return null;
        }
        return this.clientRepository
                .findByIdAndState_Active(id)
                .map(this::mapToClientDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun client avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND)
                );
    }

    @Override
    public List<ClientDto> findAll(Integer id) {
        return this.clientRepository
                .findAll()
                .stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var clientDto = this.findById(id);
        this.clientRepository.delete(this.mapToClient(clientDto));
    }

    private void checkClientDtoIsValid(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Client n'est pas valide {}", dto);
            throw new InvalidEntityException("Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
    }

    private Client mapToClient(ClientDto dto) {
        return ClientDto.builder()
                .build()
                .toEntity(dto);
    }

    private ClientDto mapToClientDto(Client client) {
        return ClientDto.builder()
                .build()
                .fromEntity(client);
    }
}
