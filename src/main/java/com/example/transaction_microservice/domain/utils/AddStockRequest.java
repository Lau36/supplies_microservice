package com.example.transaction_microservice.domain.utils;

public class AddStockRequest {
    private final Long id;
    private final Integer quantity;

    public AddStockRequest(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
