package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.CommandeClientDto;
import com.abdel.gestiondestock.DTO.LigneCommandeClientDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Article;
import com.abdel.gestiondestock.model.Client;
import com.abdel.gestiondestock.model.CommandeClient;
import com.abdel.gestiondestock.model.LigneCommandeClient;
import com.abdel.gestiondestock.repository.ArticleRepository;
import com.abdel.gestiondestock.repository.ClientRepository;
import com.abdel.gestiondestock.repository.CommandeClientRepository;
import com.abdel.gestiondestock.repository.LigneCommandeClientRepository;
import com.abdel.gestiondestock.service.CommandeClientService;
import com.abdel.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandeClientServiceImp implements CommandeClientService {
    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImp(CommandeClientRepository commandeClientRepository,LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository=ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors= CommandeClientValidator.validator(dto);
        if(!errors.isEmpty()){
            log.error("Commande client is not valid",dto);
            throw new InvalidEntityException("Commande client is not valid", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Optional<Client> client=clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with ID {} was not found in the BD ",dto.getClient().getId());
            throw new EntityNotFoundException("aucun clinet avec l id "+dto.getClient().getId()+"n'a ete trouver dans la BD ",ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors= new ArrayList<String>();
        if(dto.getLigneCommandeClients()!=null){
            dto.getLigneCommandeClients().forEach(lignCeDC->{
                if(lignCeDC.getArticle()!=null){
                    Optional<Article> article=articleRepository.findById(lignCeDC.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec id"+lignCeDC.getArticle().getId()+"n'existe pas");

                    }
                }
                else {
                    articleErrors.add("impossible de enregistre une commande avec un article null");
                }
            });
        }
        if(!articleErrors.isEmpty()){
            log.error("Article n'existe pas");
            throw new InvalidEntityException(" Article n'existe pas",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        dto.getLigneCommandeClients().forEach(lignCodClt->{
            LigneCommandeClient ligneCommandeClient= LigneCommandeClientDto.toEntity(lignCodClt);
            ligneCommandeClient.setCommandeClient(commandeClient);
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("Commande client id is null");
        }
        return commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucun commande client avec id "+id,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(code==null){
            log.error("Commande client code is null");
            return null;
        }

        return commandeClientRepository.findCommandeClientByCode(code).map(CommandeClientDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucun commande client avec code "+code,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        List<CommandeClient> commandeClient=commandeClientRepository.findAll();
        return commandeClient.stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("id is null");
        }
        commandeClientRepository.deleteById(id);
    }
}
