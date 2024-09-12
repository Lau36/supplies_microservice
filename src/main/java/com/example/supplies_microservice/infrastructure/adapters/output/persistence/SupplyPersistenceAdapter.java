package com.example.supplies_microservice.infrastructure.adapters.output.persistence;

import com.example.supplies_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.supplies_microservice.utils.Constants;

public class SupplyPersistenceAdapter implements ISupplyPersistencePort {

    @Override
    public String saveSupply() {
        return Constants.SUPPLY_CREATED;
    }
}
