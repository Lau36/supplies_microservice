package com.example.transaction_microservice.domain.utils;

public class SubtractStockRequest {
    private final Integer itemId;
    private final Integer quantity;

    public SubtractStockRequest(Integer itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
