package com.example.transaction_microservice.domain.exceptions;

public class SupplyUpdateException extends RuntimeException {
    public SupplyUpdateException(String message) {
        super(message);
    }
}
