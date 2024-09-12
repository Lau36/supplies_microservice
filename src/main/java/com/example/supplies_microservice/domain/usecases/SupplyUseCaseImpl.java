package com.example.supplies_microservice.domain.usecases;

import com.example.supplies_microservice.domain.ports.input.ISupplyUseCase;
import com.example.supplies_microservice.domain.ports.output.ISupplyPersistencePort;

public class SupplyUseCaseImpl implements ISupplyUseCase {
    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCaseImpl(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public String createSupply() {
        return supplyPersistencePort.saveSupply();
    }
}
