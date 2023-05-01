package com.pale.clientrestproject.controller;

import com.pale.clientrestproject.errorhandling.ClientNotFoundException;
import com.pale.clientrestproject.errorhandling.ClientUniqueException;
import com.pale.clientrestproject.model.Client;
import com.pale.clientrestproject.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/api/v1")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> addClient(@RequestBody @Valid Client client) throws ClientUniqueException {
            Client newClient = clientService.addClient(client);
            return  new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping("/first_name")
    public ResponseEntity<List<Client>> getByFirstName(@RequestParam("firstName") String firstName) throws ClientNotFoundException {
        List<Client> client = clientService.findByFirstName(firstName);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/last_name")
    public ResponseEntity<List<Client>> getByLastName(@RequestParam  String lastName) throws ClientNotFoundException {
        List<Client> client = clientService.findByLastName(lastName);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/idnumber")
    public ResponseEntity<Client> getByIdNumber(@RequestParam String idNumber) throws ClientNotFoundException {
        Client client = clientService.findByIdNumber(idNumber);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/mobilenumber")
    public ResponseEntity<Client> getByMobileNumber(@RequestParam String mobileNumber) throws ClientNotFoundException {
        Client client = clientService.findByMobileNumber(mobileNumber);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAll() throws ClientNotFoundException {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
