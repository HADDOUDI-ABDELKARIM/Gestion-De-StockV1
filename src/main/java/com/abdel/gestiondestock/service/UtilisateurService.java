package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findByNom(String nom);
    List<UtilisateurDto> findByPrenom(String prenom);
    List<UtilisateurDto> findAll();
    void delete(Integer id);

    UtilisateurDto findUtilisateurByEmail(String email);
}
