package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.EntrepriseDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {
    public static List<String> validate(EntrepriseDto entrepriseDto){
        List<String> errors=new ArrayList<>();
        if(entrepriseDto==null){

        }
        return errors;
    }
}
