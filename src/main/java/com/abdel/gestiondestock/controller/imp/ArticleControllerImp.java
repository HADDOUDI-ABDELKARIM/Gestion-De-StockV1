package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.ArticleDto;
import com.abdel.gestiondestock.controller.ArticleController;
import com.abdel.gestiondestock.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleControllerImp implements ArticleController {
    private ArticleService articleService;
    @Autowired
    public ArticleControllerImp(
            ArticleService articleService
    ){
        this.articleService=articleService;

    }
    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        System.out.println("id------->"+id);
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticel) {
        return articleService.findByCode(codeArticel);
    }

    @Override
    public List<ArticleDto> findAll() {

        System.out.println("id------->");

        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
