package com.example.transaction_microservice.domain.exceptions;

public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException(String message) {
        super(message);
    }
}
