package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.CommandeFournisseurDto;
import com.abdel.gestiondestock.controller.CommandeFournisseurController;
import com.abdel.gestiondestock.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurControllerImp implements CommandeFournisseurController {

    private CommandeFournisseurService commandeFournisseurService;
    @Autowired
    public CommandeFournisseurControllerImp(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService=commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
        return ResponseEntity.ok(commandeFournisseurService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer idCommandeFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.findById(idCommandeFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {

        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}