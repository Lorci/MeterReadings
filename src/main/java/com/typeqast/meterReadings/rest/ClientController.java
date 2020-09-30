package com.typeqast.meterReadings.rest;

import com.typeqast.meterReadings.model.Client;
import com.typeqast.meterReadings.rest.entity.ClientDto;
import com.typeqast.meterReadings.service.ClientService;
import com.typeqast.meterReadings.service.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("")
    public List<ClientDto> getClients() {
        return clientService.getClients().stream().map(ClientDto::new).collect(Collectors.toList());
    }

}
