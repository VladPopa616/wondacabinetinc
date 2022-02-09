package com.wondacabinetinc.wondacabinetinc.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {}

    public InvalidEmailException(String message) { super(message); }
}
