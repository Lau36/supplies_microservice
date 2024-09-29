package com.example.transaction_microservice.domain.ports.input;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.models.Supply;

import java.time.LocalDateTime;

public interface ISupplyUseCase {
    Supply addSupply(Supply supply);
    LocalDateTime getNextSupplyDate(Long idItem);
}
