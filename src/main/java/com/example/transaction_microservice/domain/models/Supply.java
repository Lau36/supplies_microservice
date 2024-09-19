package com.example.transaction_microservice.domain.models;

import java.time.LocalDateTime;

public class Supply {
    private Long id;
    private Long adminId;
    private Long itemId;
    private Integer quantity;
    private LocalDateTime supplyDate;
    private LocalDateTime nextSupplyDate;

    public Supply(Long id, Long adminId, Long itemId, Integer quantity, LocalDateTime supplyDate, LocalDateTime nextSupplyDate  ) {
        this.id = id;
        this.adminId = adminId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.supplyDate = supplyDate;
        this.nextSupplyDate = nextSupplyDate;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getSupplyDate() {return supplyDate;}

    public LocalDateTime getNextSupplyDate() {return nextSupplyDate;}

}
