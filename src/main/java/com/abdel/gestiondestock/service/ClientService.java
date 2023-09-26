package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto dto);
    ClientDto findById(Integer id);
    List<ClientDto> findByNom(String nom);
    List<ClientDto> findByPrenom(String prenom);
    List<ClientDto> findAll();
    void delete(Integer id);
}
