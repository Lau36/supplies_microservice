package com.example.transaction_microservice.domain.exceptions;

public class PurchaseException extends RuntimeException {
    public PurchaseException(String message) {
        super(message);
    }
}
