package com.example.transaction_microservice.domain.utils;

import java.time.LocalDateTime;

public class Cart {
    private final Long id;
    private final Long userId;
    private final Long itemId;
    private final Integer quantity;
    private final LocalDateTime updatedAt;
    private final String status;
    private final Boolean deleted;

    public Cart(Long id, Long userId, Long itemId, Integer quantity, LocalDateTime updatedAt, String status, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.status = status;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
