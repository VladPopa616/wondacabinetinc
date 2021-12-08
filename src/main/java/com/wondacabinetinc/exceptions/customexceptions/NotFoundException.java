package com.wondacabinetinc.exceptions.customexceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {}

    public NotFoundException(String message) { super(message); }
}
