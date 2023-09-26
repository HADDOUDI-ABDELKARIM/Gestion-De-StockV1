package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class LigneCommandeFournisseurDto {
    private Integer id;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private CommandeFournisseurDto commandFournisseur;

    private Integer idEntreprise;


    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur lignecommandeFournisseur) {
        if ((lignecommandeFournisseur == null)) {
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(lignecommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(lignecommandeFournisseur.getArticle()))
                .quantite(lignecommandeFournisseur.getQuantite())
                .prixUnitaire(lignecommandeFournisseur.getPrixUnitaire())
                .commandFournisseur(CommandeFournisseurDto.fromEntity(lignecommandeFournisseur.getCommandFournisseur()))
                .idEntreprise(lignecommandeFournisseur.getIdEntreprise()).build();
    }
    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto lignecommandeFournisseurDto){
        LigneCommandeFournisseur lignecommandeFournisseur=new LigneCommandeFournisseur();
        lignecommandeFournisseur.setId(lignecommandeFournisseurDto.getId());
        lignecommandeFournisseur.setArticle(ArticleDto.toEntity(lignecommandeFournisseurDto.getArticle()));
        lignecommandeFournisseur.setQuantite(lignecommandeFournisseurDto.getQuantite());
        lignecommandeFournisseur.setPrixUnitaire(lignecommandeFournisseurDto.getPrixUnitaire());
        lignecommandeFournisseur.setCommandFournisseur(CommandeFournisseurDto.toEntity(lignecommandeFournisseurDto.getCommandFournisseur()));
        lignecommandeFournisseur.setIdEntreprise(lignecommandeFournisseurDto.getIdEntreprise());
        return lignecommandeFournisseur;
    }

}
