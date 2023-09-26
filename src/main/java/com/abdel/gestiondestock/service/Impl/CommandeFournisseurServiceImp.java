package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.CommandeFournisseurDto;
import com.abdel.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Article;
import com.abdel.gestiondestock.model.CommandeFournisseur;
import com.abdel.gestiondestock.model.Fournisseur;
import com.abdel.gestiondestock.model.LigneCommandeFournisseur;
import com.abdel.gestiondestock.repository.ArticleRepository;
import com.abdel.gestiondestock.repository.CommandeFournisseurRepository;
import com.abdel.gestiondestock.repository.FournisseurRepository;
import com.abdel.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.abdel.gestiondestock.service.CommandeFournisseurService;
import com.abdel.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandeFournisseurServiceImp implements CommandeFournisseurService {
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImp(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
}




    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors= CommandeFournisseurValidator.validator(dto);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Commande fournisseur is not valid", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }
        Optional<Fournisseur> fournisseur =fournisseurRepository.findById(dto.getFournisseur().getId());
        if(fournisseur.isEmpty()){
            log.warn("fournisseur with ID {} was not found in the BD ",dto.getFournisseur().getId());
            throw new EntityNotFoundException("aucun fournisseur avec l id "+dto.getFournisseur().getId()+"n'a ete trouver dans la BD ",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articlerrors=new ArrayList<>();
        if(dto.getLigneCommandeFournisseur()!=null){
            dto.getLigneCommandeFournisseur().forEach(lignCeDF->{
                if(lignCeDF.getArticle()!=null) {
                    Optional<Article> article = articleRepository.findById(lignCeDF.getArticle().getId());
                    if(article.isEmpty()){
                        articlerrors.add("L'article avec id"+lignCeDF.getArticle().getId()+"n'existe pas");
                    }
                }else{
                    articlerrors.add("impossible de enregistre une commande avec un article null");
                }
            });
        }
        if(!articlerrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException(" Article n'existe pas",ErrorCodes.ARTICLE_NOT_FOUND,articlerrors);
        }
        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        dto.getLigneCommandeFournisseur().forEach(lignCodClt->{
            LigneCommandeFournisseur ligneCommandeFournisseur= LigneCommandeFournisseurDto.toEntity(lignCodClt);
            ligneCommandeFournisseur.setCommandFournisseur(commandeFournisseur);
            ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        });
        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id==null){
            log.error("Commande fournisseur id is null");
            return null;
        }

        return commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucun commande fournisseur avec id "+id,ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto  findByCode(String code) {
        if(code==null){
            log.error("Commande fournisseur code is null");
            return null;
        }

        return commandeFournisseurRepository.findCommandeFournisseurByCode(code).map(CommandeFournisseurDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucun commande fournisseur avec code "+code,ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto > findAll() {
        List<CommandeFournisseur> commandeFournisseurs= commandeFournisseurRepository.findAll();
        return commandeFournisseurs.stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("id is null");
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
