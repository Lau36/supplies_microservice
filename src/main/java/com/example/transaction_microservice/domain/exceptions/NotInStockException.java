package com.example.transaction_microservice.domain.exceptions;

public class NotInStockException extends RuntimeException {
    public NotInStockException(String message) {
        super(message);
    }
}
