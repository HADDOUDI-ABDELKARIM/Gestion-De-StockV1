package com.abdel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @Column(name = "nom")
    private String nom;
    @Column(name = "pernom")
    private String prenom;
    @Column(name = "identrerise")
    private Integer idEntreprise;
    @Embedded
    private Adresse adresse;
    @Column(name = "photo")
    private String photo;
    @Column(name = "mail")
    private String mail;
    @Column(name = "numtel")
    private String numTel;
    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;


}
