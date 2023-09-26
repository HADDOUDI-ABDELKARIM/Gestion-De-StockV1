package com.abdel.gestiondestock.controller;
import com.abdel.gestiondestock.DTO.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

public interface UtilisateurController {
    @PostMapping(value = APP_ROOT+"/utilisateurs/create" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT+"/utilisateurs/{idutilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto finById(@PathVariable("idutilisateur") Integer id);
    @GetMapping(value = APP_ROOT+"/utilisateurs/{nomutilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> finByNom(@PathVariable("nomutilisateur") String nom);
    @GetMapping(value = APP_ROOT+"/utilisateurs/{prenomutilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> finByPrenom(@PathVariable("prenomutilisateur") String prenom);
    @GetMapping(value = APP_ROOT+"/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/utilisateurs/delete/{idutilisateur}")
    void delete(@PathVariable("idutilisateur") Integer id);


}
