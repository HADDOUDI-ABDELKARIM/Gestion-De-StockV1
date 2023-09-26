package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validator(CommandeFournisseurDto dto) {

        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("Fournisseur is null");
            errors.add("veuillez renseigner le code du commande");
            errors.add("veuillez renseigner la date du commande");
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("veuillez renseigner le code du commande");
        }
        if(dto.getDateCommande()==null){
            errors.add("veuillez renseigner la date du commande");
        }
        if(dto.getFournisseur()==null){
            errors.add("Fournisseur is null");

        }

        return errors;
    }
}
