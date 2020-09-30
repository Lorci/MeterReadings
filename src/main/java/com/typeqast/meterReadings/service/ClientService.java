package com.typeqast.meterReadings.service;

import com.typeqast.meterReadings.model.Client;
import com.typeqast.meterReadings.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

}
