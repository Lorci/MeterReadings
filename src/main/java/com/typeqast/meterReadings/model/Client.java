package com.typeqast.meterReadings.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="client", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"address_street_name", "address_street_number", "address_city"})
                }
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="address_street_name")
    private String addressStreetName;

    @Column(name="address_street_number")
    private String addressStreetNumber;

    @Column(name="address_city")
    private String addressCity;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeterReading> meterReadings = new ArrayList<>();


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

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;

        if (addressStreetName.equals(client.addressStreetName) &&
                addressStreetNumber.equals(client.addressStreetNumber) &&
                addressCity.equals(client.addressCity)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressStreetName, addressStreetNumber, addressCity);
    }

    @Override
    public String toString() {
        return "Client{" +
                "addressStreetName='" + addressStreetName + '\'' +
                ", addressStreetNumber='" + addressStreetNumber + '\'' +
                ", addressCity='" + addressCity + '\'' +
                '}';
    }
}
