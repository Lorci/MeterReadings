package com.typeqast.meterReadings.util;

public class NoSuchClientException extends Exception {

    public NoSuchClientException() {
        super("Client with this address doesn't exist.");
    }
}
