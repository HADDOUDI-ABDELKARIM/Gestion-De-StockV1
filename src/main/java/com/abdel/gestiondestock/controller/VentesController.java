package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.VentesDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

public interface VentesController {
    @PostMapping(value = APP_ROOT+"/ventes/create" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VentesDto> save(@RequestBody VentesDto dto);
    @GetMapping(value =APP_ROOT+"/ventes/{idventes}" )
    ResponseEntity<VentesDto> findById(@PathVariable Integer idventes);
    @GetMapping(value =APP_ROOT+"/ventes/{codeventes}" )
    ResponseEntity<VentesDto> findByCode(@PathVariable("codeventes")String code);
    @GetMapping(value =APP_ROOT+"/ventes/all" )
    ResponseEntity<List<VentesDto>> findAll();
    @DeleteMapping(value = APP_ROOT+"/ventes/delet/{idventes}")
    ResponseEntity delete(@PathVariable("ventes") Integer id);
}
