package com.unipi.mns.mnscrm00.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class, ListConversionException.class, DataValidationException.class})
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(Exception ex) {
        Map<String, Object> responseBody = new HashMap<>();

        HttpStatusCode status;
        String message;

        if (ex instanceof ResponseStatusException) {
            ResponseStatusException rsEx = (ResponseStatusException) ex;
            status = rsEx.getStatusCode();
            message = rsEx.getReason();
        } else if (ex instanceof DataValidationException) {
            status = HttpStatus.BAD_REQUEST; // Customize the status if needed
            message = ex.getMessage();
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
            message = ex.getMessage();
        }

        responseBody.put("status", status.value());
        responseBody.put("error", status);
        responseBody.put("message", message);

        return new ResponseEntity<>(responseBody, status);
    }
}
