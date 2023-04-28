package com.pale.clientrestproject.service;

import com.pale.clientrestproject.errorhandling.ClientNotFoundException;
import com.pale.clientrestproject.errorhandling.ClientUniqueException;
import com.pale.clientrestproject.errorhandling.InvalidIDNumberException;
import com.pale.clientrestproject.model.Client;
import com.pale.clientrestproject.repository.IClientRepo;
import com.pale.clientrestproject.validation.IDValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IDValidator idValidator;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    public Client addClient(Client client) throws ClientUniqueException {

        try {
            if(!idValidator.isValidID(client.getIdNumber()))
                throw new InvalidIDNumberException("Please enter a valid South African Number");
            return clientRepo.save(client);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            logger.error("Unique constraint violated");
            throw new ClientUniqueException("Please ensure you have not registered current mobile or ID number");
        } catch (InvalidIDNumberException e) {
            logger.error("Invalid SA ID Number");
            throw new RuntimeException(e);
        }
    }

    //Will update if ID number or mobile number is the same
    public Client updateClient(Client client) throws InvalidIDNumberException {
        if(!idValidator.isValidID(client.getIdNumber())) {
            throw new InvalidIDNumberException("Please enter a valid South African Number");
        } else {
            return clientRepo.save(client);
        }


    }

    public List<Client> findByFirstName(String firstName) throws ClientNotFoundException {
        List<Client> clients = null;
        for(Client client : clientRepo.findByFirstName(firstName)) {
            clients.add(client);
        }

        if(!clients.isEmpty()) {
            return clients;
        } else {
            logger.error("No results found for first name: " + firstName);
            throw new ClientNotFoundException("No results found for first name: " + firstName);
        }
    }

    public List<Client> findByLastName(String lastName) throws ClientNotFoundException {
        List<Client> clients = null;
        for(Client client : clientRepo.findByLastName(lastName)) {
            clients.add(client);
        }

        if(!clients.isEmpty()) {
            return clients;
        } else {
            logger.error("No results found for last name: " + lastName);
            throw new ClientNotFoundException("No results found for last name: " + lastName);
        }
    }

    public Client findByIdNumber(String idNumber) throws ClientNotFoundException {
        Client client = clientRepo.findByIdNumber(idNumber);

        if(!(client == null)) {
            return client;
        } else {
            logger.error("No results found id Number: " + idNumber);
            throw new ClientNotFoundException("No results found id Number: " + idNumber);
        }


    }

    public Client findByMobileNumber(String mobileNumber) throws ClientNotFoundException {
        Client client = clientRepo.findByMobileNumber(mobileNumber);

        if(!(client == null)) {
            return client;
        } else {
            logger.error("No results found mobile Number: " + mobileNumber);
            throw new ClientNotFoundException("No results found mobile Number: " + mobileNumber);
        }
    }

    public List<Client> getAllClients() throws ClientNotFoundException {
        List<Client> clients = clientRepo.findAll();
        if(!clients.isEmpty()) {
            return clients;
        } else {
            logger.error("No clients have been registered yet");
            throw new ClientNotFoundException("No clients have been registered yet");
        }
    }
}
