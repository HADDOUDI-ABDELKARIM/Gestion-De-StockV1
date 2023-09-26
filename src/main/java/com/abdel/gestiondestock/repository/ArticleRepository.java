package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Optional<Article> findByCodeArticle(String code);


}
