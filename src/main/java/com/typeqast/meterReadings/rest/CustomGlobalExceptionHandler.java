package com.typeqast.meterReadings.rest;

import com.typeqast.meterReadings.rest.entity.CustomErrorResponse;
import com.typeqast.meterReadings.util.MeterReadingExistsException;
import com.typeqast.meterReadings.util.NoSuchClientException;
import com.typeqast.meterReadings.util.NoSuchMeterReadingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value() + " " + status.getReasonPhrase());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({NoSuchMeterReadingException.class,
            NoSuchClientException.class,
            MeterReadingExistsException.class})
    public ResponseEntity<CustomErrorResponse> customExceptionHandlerer(Exception ex, WebRequest request) {
        String error = "Bad request parameters.";

        CustomErrorResponse errors = new CustomErrorResponse(HttpStatus.NOT_FOUND, error, ex);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

    /*@ExceptionHandler
    public ResponseEntity<CustomErrorResponse> customGenericExceptionHandlerer(Exception ex, WebRequest request) {
        String error = "Bad request";

        CustomErrorResponse errors = new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }*/

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse> jsonParseExceptionHandlerer(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = "Bad request parameters, one or more parameters cannot be parsed.";

        CustomErrorResponse errors = new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Bad request body, json cannot be parsed.";

        CustomErrorResponse errors = new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Bad request, " + ex.getParameterName() + "parameter is missing.";

        CustomErrorResponse errors = new CustomErrorResponse(HttpStatus.BAD_REQUEST, error, ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
