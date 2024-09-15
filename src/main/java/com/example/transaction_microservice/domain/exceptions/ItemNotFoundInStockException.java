package com.example.transaction_microservice.domain.exceptions;

public class ItemNotFoundInStockException extends RuntimeException {
    public ItemNotFoundInStockException(String message) {
        super(message);
    }
}
