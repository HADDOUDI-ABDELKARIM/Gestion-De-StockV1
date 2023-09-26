package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    List<Utilisateur> findByNom(String nom);

    List<Utilisateur> findByPrenom(String prenom);


    Optional<Utilisateur> findUtilisateurByEmail(String email);
}
