package com.abdel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligneCommandeclient")
public class LigneCommandeClient extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
    @Column(name = "identrerise")
    private Integer idEntreprise;
    @Column(name = "quantite")
    private BigDecimal quantite;
    @Column(name = "prixUnitaire")
    private BigDecimal prixUnitaire;
    @ManyToOne
    @JoinColumn(name = "idCommandeClient")
    private CommandeClient commandeClient;
}
