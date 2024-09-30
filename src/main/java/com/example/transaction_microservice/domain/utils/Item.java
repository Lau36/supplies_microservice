package com.example.transaction_microservice.domain.utils;

import java.math.BigDecimal;

public class Item {
    private final Long itemId;
    private final String name;
    private final Integer quantity;
    private final BigDecimal price;

    public Item(Integer quantity, String name, Long itemId, BigDecimal price) {
        this.quantity = quantity;
        this.name = name;
        this.itemId = itemId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
