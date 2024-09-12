package com.example.supplies_microservice.application.services;

import com.example.supplies_microservice.domain.ports.input.ISupplyUseCase;

public class SupplyService implements ISupplyUseCase {
    private final ISupplyUseCase  supplyUseCase;

    public SupplyService(ISupplyUseCase supplyUseCase) {
        this.supplyUseCase = supplyUseCase;
    }

    @Override
    public String createSupply() {
        return supplyUseCase.createSupply();
    }
}
