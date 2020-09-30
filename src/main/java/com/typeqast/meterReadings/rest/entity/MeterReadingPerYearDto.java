package com.typeqast.meterReadings.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.typeqast.meterReadings.model.MeterReading;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterReadingPerYearDto implements Serializable {

    private Year year;

    @JsonProperty("meter-readings")
    private Map<Month, BigDecimal> monthValueMap = new HashMap<>();

    public MeterReadingPerYearDto() {

    }

    public MeterReadingPerYearDto(Year year, List<MeterReading> meterReadingList) {

        this.year = year;

        meterReadingList.forEach(mr -> {
            monthValueMap.put(mr.getMonth(), mr.getValue());
        });

    }

    public MeterReadingPerYearDto(Year year, MeterReading meterReading) {
        this.year = year;
        monthValueMap.put(meterReading.getMonth(), meterReading.getValue());
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Map<Month, BigDecimal> getMonthValueMap() {
        return monthValueMap;
    }

    public void setMonthValueMap(Map<Month, BigDecimal> monthValueMap) {
        this.monthValueMap = monthValueMap;
    }
}
