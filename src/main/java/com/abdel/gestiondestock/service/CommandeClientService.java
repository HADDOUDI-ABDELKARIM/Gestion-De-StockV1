package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);

}
