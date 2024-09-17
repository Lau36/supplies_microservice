package com.example.transaction_microservice.domain.models;

import java.time.LocalDate;

public class Supply {
    private Long id;
    private Long adminId;
    private Long itemId;
    private Integer quantity;
    private LocalDate date;

    public Supply(Long id, Long adminId, Long itemId, Integer quantity, LocalDate date) {
        this.id = id;
        this.adminId = adminId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
