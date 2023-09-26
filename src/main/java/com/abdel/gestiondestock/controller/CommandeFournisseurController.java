package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.CommandeFournisseurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;


public interface CommandeFournisseurController{

    @PostMapping(value = APP_ROOT+"/CommandesFournisseur/create")
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto);

    @GetMapping(value =APP_ROOT+"/CommandesFournisseur/{idCommandeFournisseur}" )
    ResponseEntity<CommandeFournisseurDto > findById(@PathVariable Integer idCommandeFournisseur);

    @GetMapping(value =APP_ROOT+"/CommandesFournisseur/{codeCommandeFournisseur}" )
    ResponseEntity<CommandeFournisseurDto > findByCode(@PathVariable("codeCommandeFournisseur")String code);

    @GetMapping(value =APP_ROOT+"/CommandesFournisseur/all" )
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping(value = APP_ROOT+"/CommandesFournisseur/delet/{idCommandeFournisseur}")
    ResponseEntity delete(@PathVariable("idCommandeFournisseur") Integer id);
}
