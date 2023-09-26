package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.EntrepriseDto;
import com.abdel.gestiondestock.controller.EntrepriseController;
import com.abdel.gestiondestock.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntrepriseControllerImp implements EntrepriseController {
    private EntrepriseService entrepriseService;
    @Autowired
    public EntrepriseControllerImp(EntrepriseService entrepriseService){
        this.entrepriseService=entrepriseService;
    }
    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.finById(id);
    }

    @Override
    public List<EntrepriseDto> finByNom(String nom) {
        return entrepriseService.finByNom(nom);
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
