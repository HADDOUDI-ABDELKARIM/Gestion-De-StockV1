package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Integer id);

    ArticleDto findByCode(String codeArticle);

    List<ArticleDto> findAll();

    void delete(Integer id);


}
