package com.example.transaction_microservice.domain.exceptions;

public class StockUpdateException extends RuntimeException {
    public StockUpdateException(String message) {
        super(message);
    }
}
