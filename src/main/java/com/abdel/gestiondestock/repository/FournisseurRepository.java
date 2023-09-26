package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    List<Fournisseur> findByNom(String nom);

    List<Fournisseur> findByPrenom(String prenom);
}
