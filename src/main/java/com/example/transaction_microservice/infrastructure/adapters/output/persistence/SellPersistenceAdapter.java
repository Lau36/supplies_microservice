package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.output.ISellPersistencePort;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SellRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellPersistenceAdapter implements ISellPersistencePort {

    private final SellRepository sellRepository;

    @Override
    public Sell saveSell(Sell sell) {
        return null;
    }
}
