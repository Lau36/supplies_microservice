package com.example.transaction_microservice.application.services;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;

public class SupplyService implements ISupplyUseCase {
    private final ISupplyUseCase  supplyUseCase;

    public SupplyService(ISupplyUseCase supplyUseCase) {
        this.supplyUseCase = supplyUseCase;
    }

    @Override
    public Supply addSupply(Supply supply) {
        return supplyUseCase.addSupply(supply);
    }
}
