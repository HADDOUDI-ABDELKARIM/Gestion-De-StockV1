package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors=new ArrayList<>();
        if(utilisateurDto==null){
            errors.add("veuillez renseigner le nom d'utilisateur");
            errors.add("veuillez renseigner le prenom d'utilisateur");
            errors.add("veuillez renseigner le mot de passe d'utilisateur");
            errors.add("veuillez renseigner l adresse d'utilisateur");
            errors.add("veuillez renseigner l email d'utilisateur");
            return errors;
        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("veuillez renseigner le nom d'utilisateur");

        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("veuillez renseigner le prenom d'utilisateur");

        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("veuillez renseigner l email d'utilisateur");

        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("veuillez renseigner le mot de passe d'utilisateur");

        }
        if (utilisateurDto.getDateNaissance()==null){
            errors.add("veuillez renseigner la date de naissancce");

        }
        if (utilisateurDto.getAdresse()==null) {
            errors.add("veuillez renseigner l adresse d'utilisateur");
        }else {
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("Le champs ville est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("Le champs code postel est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("Le champs pays est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("Le champs adresse 1 est obligatoire");
            }
        }

        return errors;
    }
}
