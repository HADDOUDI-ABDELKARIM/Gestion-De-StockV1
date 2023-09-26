package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
}
