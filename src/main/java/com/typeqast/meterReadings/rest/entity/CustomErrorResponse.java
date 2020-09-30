package com.typeqast.meterReadings.rest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomErrorResponse {

    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private CustomErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public CustomErrorResponse(HttpStatus status, String errorMessage, Throwable ex) {
        this();
        this.status = status.value() + " " + status.getReasonPhrase();
        this.message = errorMessage;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
