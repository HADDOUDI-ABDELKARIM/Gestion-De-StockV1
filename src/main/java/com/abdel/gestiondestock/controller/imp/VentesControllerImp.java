package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.VentesDto;
import com.abdel.gestiondestock.controller.VentesController;
import com.abdel.gestiondestock.service.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class VentesControllerImp implements VentesController {
    private VentesService ventesService;
    @Autowired
    public VentesControllerImp(VentesService ventesService) {
        this.ventesService =ventesService;
    }

    @Override
    public ResponseEntity<VentesDto> save(VentesDto dto) {
        return ResponseEntity.ok(ventesService.save(dto));
        //return ResponseEntity.status(HttpStatus.OK).body(ventesService.save(dto));
    }

    @Override
    public ResponseEntity<VentesDto> findById(Integer idventes) {
        return ResponseEntity.ok(ventesService.findById(idventes));
    }

    @Override
    public ResponseEntity<VentesDto> findByCode(String code) {

        return ResponseEntity.ok(ventesService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<VentesDto>> findAll() {
        return ResponseEntity.ok(ventesService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        ventesService.delete(id);
        return ResponseEntity.ok().build();
    }
}
