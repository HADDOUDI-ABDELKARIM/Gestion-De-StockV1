package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {
    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVente;

    public static VentesDto fromEntity(Ventes ventes) {
        if (ventes == null) {
            //TODO throw an exeption
            return null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .idEntreprise(ventes.getIdEntreprise())
                .commentaire(ventes.getCommentaire()).build();
    }
    public static Ventes toEntity(VentesDto venteDto) {
        if (venteDto == null) {
            //TODO throw an exeption
            return null;
        }
        Ventes vente=new Ventes();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaire(venteDto.getCommentaire());
        vente.setIdEntreprise(venteDto.getIdEntreprise());
        return vente;
    }
}
