package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto dto){
        List<String> errors=new ArrayList<>();
        if (dto==null){
            errors.add("veuillez renseigner le nom du Fournisseur");
            errors.add("veuillez renseigner le prenom du Fournisseur");
            errors.add("veuillez renseigner le numéro du Fournisseur");
            errors.add("veuillez renseigner l'email duFournisseur");
            return errors;


        }
        if (!StringUtils.hasLength(dto.getNom())){
            errors.add("veuillez renseigner le nom du Fournisseur");

        }
        if (!StringUtils.hasLength(dto.getPrenom())){
            errors.add("veuillez renseigner le prenom du Fournisseur");

        }
        if (!StringUtils.hasLength(dto.getNumTel())){
            errors.add("veuillez renseigner le numéro du Fournisseur");

        }
        if (!StringUtils.hasLength(dto.getMail())){
            errors.add("veuillez renseigner l'email du Fournisseur");

        }
        return errors;
    }
}
