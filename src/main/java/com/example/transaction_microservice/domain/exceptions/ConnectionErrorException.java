package com.example.transaction_microservice.domain.exceptions;

public class ConnectionErrorException extends RuntimeException {
    public ConnectionErrorException(String message) {
        super(message);
    }
}
