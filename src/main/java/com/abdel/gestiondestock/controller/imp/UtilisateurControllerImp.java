package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.UtilisateurDto;
import com.abdel.gestiondestock.controller.UtilisateurController;
import com.abdel.gestiondestock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurControllerImp implements UtilisateurController {
    public UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurControllerImp(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto finById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> finByNom(String nom) {
        return utilisateurService.findByNom(nom);
    }

    @Override
    public List<UtilisateurDto> finByPrenom(String prenom) {
        return utilisateurService.findByPrenom(prenom);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
