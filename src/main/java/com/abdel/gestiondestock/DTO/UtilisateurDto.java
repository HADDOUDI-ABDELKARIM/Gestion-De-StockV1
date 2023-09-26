package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private Instant dateNaissance;

    private String email;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RoleDto> roles;

    public  static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur==null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .motDePasse(utilisateur.getPassword())
                .photo(utilisateur.getPhoto())
                .email(utilisateur.getEmail())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise())).build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPassword(utilisateurDto.getMotDePasse());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        return utilisateur;
    }
}
