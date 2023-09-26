package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Entreprise,Integer> {
    List<Entreprise> findByNom(String nom);

}
