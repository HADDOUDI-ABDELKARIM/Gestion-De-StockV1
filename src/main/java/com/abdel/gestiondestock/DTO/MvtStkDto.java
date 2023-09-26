package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.MvStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
public class MvtStkDto{
    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private String typeMvt;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvStk mvStk) {
        if ((mvStk == null)) {
            return null;
        }
        return MvtStkDto.builder()
                .id(mvStk.getId())
                .dateMvt(mvStk.getDateMvt())
                .article(ArticleDto.fromEntity(mvStk.getArticle()))
                .quantite(mvStk.getQuantite())
                .idEntreprise(mvStk.getIdEntreprise())
                .typeMvt(mvStk.getTypeMvt()).build();
    }
    public static MvStk toEntity(MvtStkDto mvStkDto){
        MvStk mvStk=new MvStk();
        mvStk.setId(mvStkDto.getId());
        mvStk.setDateMvt(mvStkDto.getDateMvt());
        mvStk.setArticle(ArticleDto.toEntity(mvStkDto.getArticle()));
        mvStk.setQuantite(mvStkDto.getQuantite());
        mvStk.setTypeMvt(mvStkDto.typeMvt);
        mvStk.setIdEntreprise(mvStkDto.getIdEntreprise());
        return mvStk;
    }
}
