package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeClientDto {
    private Integer id;

    private String code;
    private Instant dateCommande;

    private ClientDto client;

    private Integer idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClients;
    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if ((commandeClient == null)) {
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEntreprise())
                .client(ClientDto.fromEntity(commandeClient.getClient())).build();
    }
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){
        CommandeClient commandeClient=new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        return commandeClient;
    }
}
