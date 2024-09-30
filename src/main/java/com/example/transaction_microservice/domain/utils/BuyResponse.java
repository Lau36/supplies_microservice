package com.example.transaction_microservice.domain.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BuyResponse {
    private List<Item> itemsInCart;
    private LocalDateTime lastUpdateCart;
    private BigDecimal totalPrice;

    public BuyResponse(List<Item> itemsInCart, LocalDateTime lastUpdateCart, BigDecimal totalPrice) {
        this.itemsInCart = itemsInCart;
        this.lastUpdateCart = lastUpdateCart;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemsInCart() {
        return itemsInCart;
    }

    public LocalDateTime getLastUpdateCart() {
        return lastUpdateCart;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
