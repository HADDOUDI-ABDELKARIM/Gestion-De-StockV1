package com.abdel.gestiondestock.validator;

import com.abdel.gestiondestock.DTO.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articleDto){
        List<String> errors=new ArrayList<>();
        if(articleDto==null){
            errors.add("veuillez renseigner le Designation de l article");
            errors.add("veuillez renseigner le prix unitaire de l article");
            errors.add("veuillez renseigner le TauxTva de l article");
            errors.add("veuillez renseigner le PrixUnitaire de l article");
            errors.add("veuillez selectionner une categorie");
            return errors;
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("veuillez renseigner le Designation de l article");

        }
        if (articleDto.getPrixUnitaireHt()==null){
            errors.add("veuillez renseigner le prix unitaire de l article");

        }
        if (articleDto.getTauxTva()==null){
            errors.add("veuillez renseigner le TauxTva de l article");

        }
        if (articleDto.getPrixUnitaireTtc()==null){
            errors.add("veuillez renseigner le PrixUnitaire de l article");

        }
        if (articleDto.getCategory()==null){
            errors.add("veuillez selectionner une categorie");

        }
        return errors;
    }


}
