package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    private Integer idEntreprise;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur==null){
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .idEntreprise(fournisseur.getIdEntreprise())
                .numTel(fournisseur.getNumTel()).build();
    }

    public static Fournisseur toEntity(FournisseurDto FournisseurtDto){
        Fournisseur fournisseur=new Fournisseur();
        fournisseur.setId(FournisseurtDto.getId());
        fournisseur.setNom(FournisseurtDto.getNom());
        fournisseur.setPrenom(FournisseurtDto.getPrenom());
        fournisseur.setAdresse(AdresseDto.toEntity(FournisseurtDto.getAdresse()));
        fournisseur.setPhoto(FournisseurtDto.getPhoto());
        fournisseur.setMail(FournisseurtDto.getMail());
        fournisseur.setNumTel(FournisseurtDto.getNumTel());
        fournisseur.setIdEntreprise(FournisseurtDto.getIdEntreprise());
        return fournisseur;
    }
}
