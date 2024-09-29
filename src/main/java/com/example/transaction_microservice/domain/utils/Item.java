package com.example.transaction_microservice.domain.utils;

import java.math.BigDecimal;

public class Item {
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;

    public Item(Integer quantity, String itemName,Long itemId, BigDecimal price) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.itemId = itemId;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
