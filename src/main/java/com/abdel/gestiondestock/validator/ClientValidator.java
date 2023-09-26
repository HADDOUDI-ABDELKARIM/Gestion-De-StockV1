package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto dto){
    List<String> errors=new ArrayList<>();
        if (dto==null){
        errors.add("veuillez renseigner le nom du client");
        errors.add("veuillez renseigner le prenom du client");
        errors.add("veuillez renseigner le numéro du client");
        errors.add("veuillez renseigner l'email du client");
        return errors;


    }
        if (!StringUtils.hasLength(dto.getNom())){
        errors.add("veuillez renseigner le nom du client");

    }
        if (!StringUtils.hasLength(dto.getPrenom())){
        errors.add("veuillez renseigner le prenom du client");

    }
        if (!StringUtils.hasLength(dto.getNumTel())){
        errors.add("veuillez renseigner le numéro du client");

    }
        if (!StringUtils.hasLength(dto.getMail())){
        errors.add("veuillez renseigner l'email du client");

    }
        return errors;
}
}
