package com.typeqast.meterReadings.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Year;

public class MeterReadingPerYearSumDto implements Serializable {

    private Year year;

    @JsonProperty("meter-reading-total")
    private BigDecimal sum;

    public MeterReadingPerYearSumDto(Year year, BigDecimal sum) {
        this.year = year;
        this.sum = sum;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
