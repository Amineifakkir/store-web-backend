package com.example.sales.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = {
                "*"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping(path="api/v1/client")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping(path = "DD")
    public List<ClientDDTO> getClientsDD(){
        return clientService.getClientsDD();
    }
    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }
    @PostMapping
    public List<Client> registerNewClient(@RequestBody Client client){
        clientService.addNewClient(client);
        return clientService.getClients();
    }
    @DeleteMapping(path = "{clientId}")
    public List<Client> deleteClient(@PathVariable("clientId") Long clientId){
        clientService.deleteClient(clientId);
        return clientService.getClients();
    }
    @PutMapping(path = "{clientId}")
    public List<Client> updateClient(
            @PathVariable("clientId") Long clientId,
            @RequestBody Client client){
        clientService.updateClient(client,clientId);
        return clientService.getClients();
    }
}
