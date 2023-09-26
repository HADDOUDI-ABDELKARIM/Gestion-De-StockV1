package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

public interface EntrepriseController {
    @PostMapping(value = APP_ROOT+"/entreprise/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value =APP_ROOT+"/entreprise/{identreprise} ",produces =MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("identreprise") Integer id);

    @GetMapping(value = APP_ROOT+"/entreprise/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> finByNom(@PathVariable("nom") String nom);

    @DeleteMapping(value = APP_ROOT+"/entreprise/delete/{identreprise}")
    void delete(@PathVariable("identreprise") Integer id);
}