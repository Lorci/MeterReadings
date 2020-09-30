package com.typeqast.meterReadings.util;

public class MeterReadingExistsException extends Exception {

    public MeterReadingExistsException() {
        super("Meter reading exists.");
    }
}
