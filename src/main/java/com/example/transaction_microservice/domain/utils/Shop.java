package com.example.transaction_microservice.domain.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Shop {
    private final List<Item> itemsInCart;
    private final String userEmail;
    private final LocalDateTime lastUpdateCart;
    private final LocalDateTime shopDate;
    private final BigDecimal totalPrice;

    public Shop(List<Item> itemsInCart, String userEmail, LocalDateTime lastUpdateCart, LocalDateTime shopDate, BigDecimal totalPrice) {
        this.itemsInCart = itemsInCart;
        this.userEmail = userEmail;
        this.lastUpdateCart = lastUpdateCart;
        this.shopDate = shopDate;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemsInCart() {
        return itemsInCart;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getLastUpdateCart() {
        return lastUpdateCart;
    }

    public LocalDateTime getShopDate() {
        return shopDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
