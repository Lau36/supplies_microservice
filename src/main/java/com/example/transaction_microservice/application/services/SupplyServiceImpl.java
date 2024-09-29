package com.example.transaction_microservice.application.services;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;

import java.time.LocalDateTime;

public class SupplyServiceImpl implements SupplyService {

    private final ISupplyUseCase supplyUseCase;

    public SupplyServiceImpl(ISupplyUseCase supplyUseCase) {
        this.supplyUseCase = supplyUseCase;
    }

    @Override
    public Supply addSupply(Supply supply) {
        return supplyUseCase.addSupply(supply);
    }

    @Override
    public LocalDateTime getNextSupplyDate(Long itemId) {
        return supplyUseCase.getNextSupplyDate(itemId);
    }
}
