package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.ArticleDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;

import java.util.List;

public interface ArticleController {
    @PostMapping(value = APP_ROOT+"/articles/create",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT+"/articles/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable(name = "idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT+"/articles/t/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticel);


    @GetMapping(value = APP_ROOT+"/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();


    @DeleteMapping(value = APP_ROOT+"articles/delete/{idArticle}")
    void delete(@PathVariable("idArticles") Integer id);



}
