package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.ClientDto;
import com.abdel.gestiondestock.controller.ClientController;
import com.abdel.gestiondestock.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientControllerImp implements ClientController {
    ClientService clientService;
    @Autowired
    public ClientControllerImp(ClientService clientService){
        this.clientService=clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto finById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> finByNom(String  nom) {
        return clientService.findByNom(nom);
    }

    @Override
    public List<ClientDto> finByPrenom(String prenom) {
        return clientService.findByPrenom(prenom);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
