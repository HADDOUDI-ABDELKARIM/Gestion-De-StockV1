package com.abdel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligneCommandeFournisseur")
public class LigneCommandeFournisseur extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "idarticle")

    private Article article;
    @Column(name = "quantite")
    private BigDecimal quantite;
    @Column(name = "prixUnitaire")
    private BigDecimal prixUnitaire;
    @ManyToOne
    @JoinColumn(name = "idcommandeFournisseur")
    private CommandeFournisseur commandFournisseur;
    @Column(name = "identrerise")
    private Integer idEntreprise;

}
