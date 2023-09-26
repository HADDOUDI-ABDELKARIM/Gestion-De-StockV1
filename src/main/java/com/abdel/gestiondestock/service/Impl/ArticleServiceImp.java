package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.ArticleDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.handlers.ErrorDto;
import com.abdel.gestiondestock.model.Article;
import com.abdel.gestiondestock.repository.ArticleRepository;
import com.abdel.gestiondestock.service.ArticleService;
import com.abdel.gestiondestock.validator.ArticleValidator;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImp implements ArticleService {

    private ArticleRepository articleRepository;


    @Autowired
    public ArticleServiceImp(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }
    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors= ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}",dto);
            throw new InvalidEntityException("L'article n'est valide", ErrorCodes.ARTICLE_NOT_VALID,errors);

        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if(id==null){
            log.error("id is null {}" ,id);

        }
        Optional<Article> article=articleRepository.findById(id);
        ArticleDto dto=ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException("Aucun article avec id = "+id+"n'ete trouver dans la BDA"
                        ,ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCode(String codeArticle) {
        if(codeArticle==null){
            log.error("le code est null");
        }
        Optional<Article> article=articleRepository.findByCodeArticle(codeArticle);
        ArticleDto dto=ArticleDto.fromEntity(article.get());


        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException("Aucun article avec code = "+codeArticle+"n'ete trouver dans la BDA"
                        ,ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("id is null");
        }
        articleRepository.deleteById(id);
    }
}
