package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.CommandeClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

public interface CommandeClientController {


    @PostMapping(value = APP_ROOT+"/CommandesClients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping(value =APP_ROOT+"/CommandesClients/{idCommandeClient}" )
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

    @GetMapping(value =APP_ROOT+"/CommandesClients/{codeCommandeClient}" )
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient")String code);

    @GetMapping(value =APP_ROOT+"/CommandesClients/all" )
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT+"/CommandesClients/delet/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);
}