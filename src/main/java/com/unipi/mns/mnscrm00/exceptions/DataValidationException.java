package com.unipi.mns.mnscrm00.exceptions;

import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

public class DataValidationException extends Exception {

    public DataValidationException(String message){
        super(message);
    }
}
