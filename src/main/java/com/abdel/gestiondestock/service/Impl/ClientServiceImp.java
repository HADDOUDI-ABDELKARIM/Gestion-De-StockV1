package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.ClientDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Client;
import com.abdel.gestiondestock.repository.ClientRepository;
import com.abdel.gestiondestock.service.ClientService;
import com.abdel.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImp implements ClientService {

    private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors= ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Client is not validd {}" ,dto);
            throw new InvalidEntityException("Client n'est pas valide" , ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return dto.fromEntity(clientRepository.save(dto.toEntity(dto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id==null){
            log.error("id is null");
        }
        Optional<Client> client=clientRepository.findById(id);
        ClientDto dto=ClientDto.fromEntity(client.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException( "Aucun client avec id = "+id+"n'ete trouver dans la BDA"
                        , ErrorCodes.CLIENT_NOT_FOUND));    }

    @Override
    public List<ClientDto> findByNom(String nom) {
        if(nom== null){
            log.error("nom is not valid");
        }
        List<Client> client=clientRepository.findByNom(nom);
        if (!client.isEmpty()){
            throw new InvalidEntityException("Aucun client avec nom = "+nom+"n'ete trouver dans la BDA" , ErrorCodes.CLIENT_NOT_FOUND);

        }
        return client.stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findByPrenom(String prenom) {
        if(prenom==null){
            log.error("prenom is not valid");
        }
        List<Client> clients=clientRepository.findByPrenom(prenom);
        if (!clients.isEmpty()){
            throw new InvalidEntityException("Aucun client avec prenom = "+prenom+"n'ete trouver dans la BDA" , ErrorCodes.CLIENT_NOT_FOUND);

        }

        return clients.stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("id is not valid");
            return;
        }
        clientRepository.deleteById(id);

    }
}
