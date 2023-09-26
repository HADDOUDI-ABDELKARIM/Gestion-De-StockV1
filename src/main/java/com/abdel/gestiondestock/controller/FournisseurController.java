package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

public interface FournisseurController {


    @PostMapping(value = APP_ROOT+"/fournisseurs/create" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT+"/fournisseurs/{idfournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto finById(@PathVariable("idfournisseur") Integer id);

    @GetMapping(value = APP_ROOT+"/fournisseurs/{nomfournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> finByNom(@PathVariable("nomfournisseur") String nom);

    @GetMapping(value = APP_ROOT+"/fournisseurs/{prenomfournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> finByPrenom(@PathVariable("prenomfournisseur") String prenom);

    @GetMapping(value = APP_ROOT+"/fournisseurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/fournisseurs/delete/{idfournisseur}")
    void delete(@PathVariable("idfournisseur") Integer id);
}

