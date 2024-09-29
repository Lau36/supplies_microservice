package com.example.transaction_microservice.domain.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BuyResponse {
    private List<Item> itemsInCartId;
    private LocalDateTime lastUpdateCart;
    private BigDecimal totalPrice;

    public BuyResponse(List<Item> itemsInCartId, LocalDateTime lastUpdateCart, BigDecimal totalPrice) {
        this.itemsInCartId = itemsInCartId;
        this.lastUpdateCart = lastUpdateCart;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemsInCartId() {
        return itemsInCartId;
    }

    public LocalDateTime getLastUpdateCart() {
        return lastUpdateCart;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
