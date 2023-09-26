package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.UtilisateurDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Utilisateur;
import com.abdel.gestiondestock.repository.UtilisateurRepository;
import com.abdel.gestiondestock.service.UtilisateurService;
import com.abdel.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImp implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImp(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository =utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors= UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Utilisateur is not validd {}" ,dto);
            throw new InvalidEntityException("Utilisateur n'est pas valide" , ErrorCodes.Utilisateur_NOT_VALID,errors);
        }
        return dto.fromEntity(utilisateurRepository.save(dto.toEntity(dto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if(id==null){
            log.error("id is not valid");
        }
        Optional<Utilisateur> utilisateur= utilisateurRepository.findById(id);
        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun utilisateur avec id = "+id+"n'ete trouver dans la BDA", ErrorCodes.UTILISATEUR_FOUND));
    }

    @Override
    public List<UtilisateurDto> findByNom(String nom) {
        if(nom==null){
            log.error("nom is null");
        }
        List<Utilisateur> utilisateurs= utilisateurRepository.findByNom(nom);
        if(!utilisateurs.isEmpty()){
            throw new EntityNotFoundException("Aucun utilisateurs avec nom = "+nom+"n'ete trouver dans la BDA" , ErrorCodes.UTILISATEUR_FOUND);
        }
        return utilisateurs.stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDto> findByPrenom(String prenom) {
        if(prenom==null){
            log.error("prenom is null");
        }
        List<Utilisateur> utilisateurs= utilisateurRepository.findByPrenom(prenom);
        if(!utilisateurs.isEmpty()){
            throw new EntityNotFoundException("Aucun Utilisateur avec nom = "+prenom+"n'ete trouver dans la BDA" , ErrorCodes.UTILISATEUR_FOUND);
        }
        return utilisateurs.stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("id is null");
        }
        utilisateurRepository.deleteById(id);

    }

    @Override
    public UtilisateurDto findUtilisateurByEmail(String email) {

        return utilisateurRepository.findUtilisateurByEmail(email).map(UtilisateurDto::fromEntity).orElseThrow(()->
                new EntityNotFoundException("Aucun Utilisateur avec nom = "+email+"n'ete trouver dans la BDA" , ErrorCodes.UTILISATEUR_FOUND));
    }
}
