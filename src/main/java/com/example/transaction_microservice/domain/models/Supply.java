package com.example.transaction_microservice.domain.models;

import java.time.LocalDate;

public class Supply {
    private Long id;
    private Long adminId;
    private Long itemId;
    private Integer quantity;
    private LocalDate supplyDate;
    private LocalDate nextSupplyDate;

    public Supply(Long id, Long adminId, Long itemId, Integer quantity, LocalDate supplyDate, LocalDate nextSupplyDate  ) {
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

    public LocalDate getSupplyDate() {return supplyDate;}

    public LocalDate getNextSupplyDate() {return nextSupplyDate;}

}
