package com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sell")
public class SellEntity {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private LocalDateTime sellDate;
}
