package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Integer id;

    private String roleName;

    private UtilisateurDto utilisateur;

    private Integer idEntreprise;


    public static RoleDto fromEntity(Role role) {
        if (role == null) {
            //TODO throw an exeption
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .idEntreprise(role.getIdEntreprise())
                .utilisateur(UtilisateurDto.fromEntity(role.getUtilisateur())).build();
    }
    public static Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            //TODO throw an exeption
            return null;
        }
        Role role=new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setIdEntreprise(roleDto.getIdEntreprise());
        role.setUtilisateur(UtilisateurDto.toEntity(roleDto.getUtilisateur()));
        return role;
    }
}
