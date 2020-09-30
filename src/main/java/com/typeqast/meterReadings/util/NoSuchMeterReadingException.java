package com.typeqast.meterReadings.util;

public class NoSuchMeterReadingException extends Exception {

    public NoSuchMeterReadingException() {
        super("No such meter reading.");
    }

}
