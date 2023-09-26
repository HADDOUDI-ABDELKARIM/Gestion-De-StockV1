package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.FournisseurDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Fournisseur;
import com.abdel.gestiondestock.repository.FournisseurRepository;
import com.abdel.gestiondestock.service.FournisseurService;
import com.abdel.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImp implements FournisseurService {
    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImp(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository=fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors= FournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not validd {}" ,dto);
            throw new InvalidEntityException("Fournisseur n'est pas valide" , ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }
        return dto.fromEntity(fournisseurRepository.save(dto.toEntity(dto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id==null){
            log.error("id is not valid");
        }
        Optional<Fournisseur> fournisseur=fournisseurRepository.findById(id);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun fournisseur avec id = "+id+"n'ete trouver dans la BDA", ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findByNom(String nom) {
        if(nom==null){
            log.error("nom is null");
        }
        List<Fournisseur> fournisseur=fournisseurRepository.findByNom(nom);
        if(!fournisseur.isEmpty()){
            throw new EntityNotFoundException("Aucun Fournisseur avec nom = "+nom+"n'ete trouver dans la BDA" , ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        return fournisseur.stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<FournisseurDto> findByPrenom(String prenom) {
        if(prenom==null){
            log.error("prenom is null");
        }
        List<Fournisseur> fournisseur=fournisseurRepository.findByPrenom(prenom);
        if(!fournisseur.isEmpty()){
            throw new EntityNotFoundException("Aucun Fournisseur avec nom = "+prenom+"n'ete trouver dans la BDA" , ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        return fournisseur.stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("id is null");
        }
        fournisseurRepository.deleteById(id);
    }
}
