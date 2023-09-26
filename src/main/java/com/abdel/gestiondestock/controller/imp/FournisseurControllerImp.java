package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.FournisseurDto;
import com.abdel.gestiondestock.controller.FournisseurController;
import com.abdel.gestiondestock.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurControllerImp implements FournisseurController {
    public FournisseurService fournisseurService;
    @Autowired
    public FournisseurControllerImp(FournisseurService fournisseurService){
        this.fournisseurService=fournisseurService;
    }
    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto finById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> finByNom(String nom) {
        return fournisseurService.findByNom(nom);
    }

    @Override
    public List<FournisseurDto> finByPrenom(String prenom) {
        return fournisseurService.findByPrenom(prenom);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);

    }

}
