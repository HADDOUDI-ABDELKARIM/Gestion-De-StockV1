package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;
import java.util.List;

public interface ClientController {
    @PostMapping(value = APP_ROOT+"/clients/create" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT+"/clients/{idclient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto finById(@PathVariable("idclient") Integer id);
    @GetMapping(value = APP_ROOT+"/clients/{nomclient}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> finByNom(@PathVariable("nomclient") String nom);
    @GetMapping(value = APP_ROOT+"/clients/{prenomclient}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> finByPrenom(@PathVariable("prenomclient") String prenom);
    @GetMapping(value = APP_ROOT+"/clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();
    @DeleteMapping(value = APP_ROOT+"/clients/delete/{idclient}")
    void delete(@PathVariable("idclient") Integer id);


}
