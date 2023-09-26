package com.abdel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligneVente")
public class LigneVente extends AbstractEntity {
    @ManyToOne
    private Article article;
    @ManyToOne
    @JoinColumn(name = "idvente")
    private Ventes vente;
    @Column(name = "identrerise")
    private Integer idEntreprise;
    @Column(name = "quantite")
    private BigDecimal quantite;
    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;
}
