package com.example.sales.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    private  final ClientRepository clientRepository;
    @Autowired
    public  ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }
    public void addNewClient(Client client) {
        clientRepository.save(client);
    }
    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if(!exists){
            throw new IllegalStateException("Client with Id "
                    + clientId
                    + " does not exists");
        }
        clientRepository.deleteById(clientId);
    }
    @Transactional
    public void updateClient(Client pClient,Long clientId) {

        String nom = pClient.getNom();
        String prenom = pClient.getPrenom();
        String adresse = pClient.getAdresse();
        String numTel = pClient.getNumTel();
        String ville = pClient.getVille();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new IllegalStateException("Client with Id " + clientId + " does not exists"));
        if( nom != null && !Objects.equals(client.getNom(),nom)){
            client.setNom(nom);
        }
        if( prenom != null && !Objects.equals(client.getPrenom(),prenom)){
            client.setPrenom(prenom);
        }
        if( adresse != null && !Objects.equals(client.getAdresse(),adresse)){
            client.setAdresse(adresse);
        }
        if( numTel != null && !Objects.equals(client.getNumTel(),numTel)){
            client.setNumTel(numTel);
        }
        if( ville != null && !Objects.equals(client.getVille(),ville)){
            client.setVille(ville);
        }
    }

    public List<ClientDDTO> getClientsDD() {
        // Generate a list of ClientDDTO objects
        List<Client> clients = clientRepository.findAll();
        List<ClientDDTO> clientsDD = new ArrayList<>();
        for (Client client : clients) {
            clientsDD.add(new ClientDDTO(client.getId(), client.getNom() + " " + client.getPrenom()));
        }
        return clientsDD;
    }
}
