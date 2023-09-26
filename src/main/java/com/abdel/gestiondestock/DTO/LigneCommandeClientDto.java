package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {
    private Integer id;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private CommandeClientDto commandeClient;

    private Integer idEntreprise;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient lignecommandeClient) {
        if ((lignecommandeClient == null)) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(lignecommandeClient.getId())
                .article(ArticleDto.fromEntity(lignecommandeClient.getArticle()))
                .quantite(lignecommandeClient.getQuantite())
                .prixUnitaire(lignecommandeClient.getPrixUnitaire())
                .idEntreprise(lignecommandeClient.getIdEntreprise())
                .commandeClient(CommandeClientDto.fromEntity(lignecommandeClient.getCommandeClient())).build();
    }
    public static LigneCommandeClient toEntity(LigneCommandeClientDto lignecommandeClientDto){
        LigneCommandeClient lignecommandeClient=new LigneCommandeClient();
        lignecommandeClient.setId(lignecommandeClientDto.getId());
        lignecommandeClient.setArticle(ArticleDto.toEntity(lignecommandeClientDto.getArticle()));
        lignecommandeClient.setQuantite(lignecommandeClientDto.getQuantite());
        lignecommandeClient.setPrixUnitaire(lignecommandeClientDto.getPrixUnitaire());
        lignecommandeClient.setCommandeClient(CommandeClientDto.toEntity(lignecommandeClientDto.getCommandeClient()));
        lignecommandeClient.setIdEntreprise(lignecommandeClientDto.getIdEntreprise());
        return lignecommandeClient;
    }
}
