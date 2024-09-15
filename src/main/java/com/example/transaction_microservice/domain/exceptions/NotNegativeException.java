package com.example.transaction_microservice.domain.exceptions;

public class NotNegativeException extends RuntimeException {
    public NotNegativeException(String message) {
        super(message);
    }
}
