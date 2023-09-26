package com.abdel.gestiondestock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
public class Role extends AbstractEntity{
    @Column(name = "rolename")
    private String roleName;
    @Column(name = "identrerise")
    private Integer idEntreprise;
    @ManyToOne
    private Utilisateur utilisateur;

}
