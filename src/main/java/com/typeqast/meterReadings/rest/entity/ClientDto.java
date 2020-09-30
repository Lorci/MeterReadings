package com.typeqast.meterReadings.rest.entity;

import com.typeqast.meterReadings.model.Client;

import java.io.Serializable;

public class ClientDto implements Serializable {

    private String addressStreetName;

    private String addressStreetNumber;

    private String addressCity;

    public ClientDto(Client c) {
        this.addressStreetName = c.getAddressStreetName();
        this.addressStreetNumber = c.getAddressStreetNumber();
        this.addressCity = c.getAddressCity();
    }

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public void setAddressStreetName(String addressStreetName) {
        this.addressStreetName = addressStreetName;
    }

    public String getAddressStreetNumber() {
        return addressStreetNumber;
    }

    public void setAddressStreetNumber(String addressStreetNumber) {
        this.addressStreetNumber = addressStreetNumber;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
}
