package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.LigneVenteDto;
import com.abdel.gestiondestock.DTO.VentesDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Article;
import com.abdel.gestiondestock.model.LigneVente;
import com.abdel.gestiondestock.model.Ventes;
import com.abdel.gestiondestock.repository.ArticleRepository;
import com.abdel.gestiondestock.repository.LigneVenteRepository;
import com.abdel.gestiondestock.repository.VentesRepository;
import com.abdel.gestiondestock.service.VentesService;
import com.abdel.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImp implements VentesService {
    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImp(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }
    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors= VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        List<String> errorsArticle=new ArrayList<>();
        dto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article=articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()){
                errorsArticle.add("Aucun articel avec "+ligneVenteDto.getArticle().getId()+"n'a ete treouve dans la BDD");
            }
        });
        if(!errorsArticle.isEmpty()){
            log.error("Vente n'est pas valide Cose article not fond",errors);
            throw new InvalidEntityException("Vente n'est pas valide Cose article not fond",ErrorCodes.VENTE_NOT_VALID,errors);
        }
        Ventes saveVentes=ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente= LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(saveVentes);
            ligneVenteRepository.save(ligneVente);


        });
        return VentesDto.fromEntity(saveVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if(id==null){
            log.error(" vente id is not valid");
        }
        return ventesRepository.findById(id).map(VentesDto::fromEntity).orElseThrow(()->
                new EntityNotFoundException("Aucun vente avec id "+id+"dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if(code==null){
            log.error(" vente code is not valid");
        }
        return ventesRepository.findVentesByCode(code).map(VentesDto::fromEntity).orElseThrow(()->
                new EntityNotFoundException("Aucun vente avec code "+code+"dans la BDD", ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error(" vente id is null");
            return;
        }
        ventesRepository.deleteById(id);

    }
}
