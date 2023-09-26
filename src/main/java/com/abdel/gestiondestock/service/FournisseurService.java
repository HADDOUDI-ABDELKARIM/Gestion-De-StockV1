package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findByNom(String nom);
    List<FournisseurDto> findByPrenom(String prenom);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
