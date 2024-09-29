package com.example.transaction_microservice.domain.models;

import com.example.transaction_microservice.domain.utils.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Sell {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private LocalDateTime sellDate;

    public Sell(Long id,  Long userId, BigDecimal totalPrice, LocalDateTime sellDate) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.sellDate = sellDate;
    }

    public Long getId() {
        return id;
    }


    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getSellDate() {
        return sellDate;
    }
}
