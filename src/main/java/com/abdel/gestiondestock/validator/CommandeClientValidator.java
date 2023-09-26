package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator{
    public static List<String> validator(CommandeClientDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("client is null");
            errors.add("veuillez renseigner le code du commande");
            errors.add("veuillez renseigner la date du commande");
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("veuillez renseigner le code du commande");
        }
        if(dto.getDateCommande()==null){
            errors.add("veuillez renseigner la date du commande");
        }
        if(dto.getClient()==null){
            errors.add("client is null");

        }

        return errors;
    }
}
