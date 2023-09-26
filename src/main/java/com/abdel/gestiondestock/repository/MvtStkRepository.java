package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.MvStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepository extends JpaRepository<MvStk,Integer> {
    @Query("select sum(m.quantite) from MvStk m where m.article.id = :idArticle")
    BigDecimal stockReelArticle(@Param("idArticle") Integer idArticle);

    List<MvStk> findAllByArticleId(Integer idArticle);

}