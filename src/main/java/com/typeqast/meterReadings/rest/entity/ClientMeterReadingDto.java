package com.typeqast.meterReadings.rest.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

public class ClientMeterReadingDto implements Serializable {

    @NotNull(message = "Address street name cannot be missing or empty")
    private String addressStreetName;

    @NotNull(message = "Address street number cannot be missing or empty")
    private String addressStreetNumber;

    @NotNull(message = "Address city cannot be missing or empty")
    private String addressCity;

    @NotNull(message = "Year cannot be missing or empty")
    private Year year;

    @NotNull(message = "Month cannot be missing or empty")
    private Month month;

    @NotNull(message = "Value cannot be missing or empty")
    @DecimalMin(value = "0", message = "Value should be >= 0")
    private BigDecimal value;

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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClientMeterReadingDto{" +
                "addressStreetName='" + addressStreetName + '\'' +
                ", addressStreetNumber='" + addressStreetNumber + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", value=" + value +
                '}';
    }
}
