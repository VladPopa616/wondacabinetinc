package com.wondacabinetinc.wondacabinetinc.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {}

    public InvalidInputException(String message) { super(message); }
}
