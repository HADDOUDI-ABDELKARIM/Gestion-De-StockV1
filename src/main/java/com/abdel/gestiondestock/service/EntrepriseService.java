package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);
    EntrepriseDto finById(Integer id);
    List<EntrepriseDto> finByNom(String nom);
    List<EntrepriseDto> finAll();
    void delete(Integer id);
}
