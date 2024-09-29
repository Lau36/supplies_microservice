package com.example.transaction_microservice.application.services;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.models.Supply;

import java.time.LocalDateTime;

public interface SupplyService {
    Supply addSupply(Supply supply);
    LocalDateTime getNextSupplyDate(Long itemId);
}
