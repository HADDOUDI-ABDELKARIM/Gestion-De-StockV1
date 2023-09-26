package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.EntrepriseDto;
import com.abdel.gestiondestock.DTO.RoleDto;
import com.abdel.gestiondestock.DTO.UtilisateurDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Entreprise;
import com.abdel.gestiondestock.repository.EnterpriseRepository;
import com.abdel.gestiondestock.repository.RoleRepository;
import com.abdel.gestiondestock.service.EntrepriseService;
import com.abdel.gestiondestock.service.UtilisateurService;
import com.abdel.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EntrepriseServiceImp implements EntrepriseService {

    private EnterpriseRepository enterpriseRepository;
    private UtilisateurService utilisateurService;
    private RoleRepository roleRepository;

    @Autowired
    public EntrepriseServiceImp(
            EnterpriseRepository enterpriseRepository,
            UtilisateurService utilisateurService,
            RoleRepository roleRepository
    ){
        this.enterpriseRepository=enterpriseRepository;
        this.utilisateurService=utilisateurService;
        this.roleRepository=roleRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors= EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("entreprise is not valide",dto);
            throw new InvalidEntityException("entreprise is not valide", ErrorCodes.ENTREPRISE_NOT_FOUND,errors);
        }
        EntrepriseDto savedEntreprise=EntrepriseDto.fromEntity(enterpriseRepository.save(EntrepriseDto.toEntity(dto)));
        UtilisateurDto utilisateurDto=fromEntreprise(savedEntreprise);
        UtilisateurDto savedUser=utilisateurService.save(utilisateurDto);
        RoleDto roleDto=RoleDto.builder()
                .roleName("ADMIN").utilisateur(savedUser).build();
        roleRepository.save(RoleDto.toEntity(roleDto));
        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder().adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generatRandomPassword())
                .entreprise(dto)
                .dateNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generatRandomPassword() {
        return "r@haddoudi";
    }



    @Override
    public EntrepriseDto finById(Integer id) {
        Optional<Entreprise> entreprise=enterpriseRepository.findById(id);
        EntrepriseDto dto=EntrepriseDto.fromEntity(entreprise.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException("Aucun Entreprise avec id = "+id+"n'ete trouver dans la BDA" , ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> finByNom(String nom) {
        if(nom==null){
            log.error("nom is not valid");
        }
        List<Entreprise> entreprises=enterpriseRepository.findByNom(nom);
        if(!entreprises.isEmpty()){
            throw  new EntityNotFoundException("Aucun entrepise avec id = "+nom+"n'ete trouver dans la BDA"
                    ,ErrorCodes.ENTREPRISE_NOT_FOUND);
        }
        return entreprises.stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<EntrepriseDto> finAll() {
        List<Entreprise> entreprise=enterpriseRepository.findAll();
        return entreprise.stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error(("id is not valid"));
        }
        enterpriseRepository.deleteById(id);

    }
}
